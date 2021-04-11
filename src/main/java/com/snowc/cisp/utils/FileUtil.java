package com.snowc.cisp.utils;

import com.google.gson.Gson;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import java.io.File;
import java.util.UUID;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;



public class FileUtil {

  private static final String ACCESS_KEY = "pxnoB7DKZRVjxsBUFmvaY-OI-neUZF9i5xTEvI9d";

  private static final String SECRET_KEY = "qPkqRsoFhWjh3qR4WwftOKFiWG0GNQhvLa4KDfxW";

  private static final String PREFIX_URL = "qpzry9s81.hn-bkt.clouddn.com/";

  private static final String BUCKET = "snowc";

  public static String upload(MultipartFile originFile) {
    try {
      String filename = "";
      if (originFile.getOriginalFilename() != null) {
        filename = originFile.getOriginalFilename();
      }
      File file = new File(filename);
      FileUtils.copyInputStreamToFile(originFile.getInputStream(), file);
      UploadManager uploadManager = getUploadManager();

      String token = getToken();
      Response response = uploadManager.put(file.getAbsolutePath(), newName(file.getName()), token);

      //解析上传成功的结果
      DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
      if (file.delete()) {
        return PREFIX_URL + putRet.key;
      }
      return null;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public static boolean delete(String key) {
    try {
      BucketManager bkm = getBucketManager();
      bkm.delete(BUCKET, key);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  private static UploadManager getUploadManager() {
    Configuration cfg = new Configuration(Zone.zone0());
    return new UploadManager(cfg);
  }

  private static BucketManager getBucketManager() {
    Configuration cfg = new Configuration(Zone.zone0());
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    return new BucketManager(auth, cfg);
  }

  private static String newName(String oldName) {
    String[] datas = oldName.split("\\.");
    String type = datas[datas.length - 1];
    return UUID.randomUUID().toString() + "." + type;
  }

  private static String getToken() {
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    return auth.uploadToken(BUCKET);
  }
}
