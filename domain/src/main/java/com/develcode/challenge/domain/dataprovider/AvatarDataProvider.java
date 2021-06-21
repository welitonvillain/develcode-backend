package com.develcode.challenge.domain.dataprovider;

import java.io.File;
import java.io.InputStream;

public interface AvatarDataProvider {

  String uploadAvatar(InputStream fileInputStream, String name);

  File showAvatar(String fileName);

}
