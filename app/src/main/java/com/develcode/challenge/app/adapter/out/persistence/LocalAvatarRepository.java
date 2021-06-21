package com.develcode.challenge.app.adapter.out.persistence;

import com.develcode.challenge.domain.dataprovider.AvatarDataProvider;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;

import javax.inject.Named;
import java.io.*;
import java.util.UUID;

@Named("repositoryAvatar")
public class LocalAvatarRepository implements AvatarDataProvider {

  @Value("${resources.avatar.url}")
  private String path;

  private final String rootDir = System.getProperty("user.dir");

  @Override
  public String uploadAvatar(InputStream fileInputStream, String name) {
    try {
      String randomName = UUID.randomUUID() + "-" + name;
      File targetFile = new File(rootDir + path + randomName);

      OutputStream outStream = new FileOutputStream(targetFile);

      byte[] buffer = new byte[8 * 1024];
      int bytesRead;
      while ((bytesRead = fileInputStream.read(buffer)) != -1) {
        outStream.write(buffer, 0, bytesRead);
      }

      IOUtils.closeQuietly(fileInputStream);
      return randomName;
    } catch (IOException ex) {
      ex.printStackTrace();

      return null;
    }
  }

  @Override
  public File showAvatar(String fileName) {
    return new File(rootDir + path + fileName);
  }
}
