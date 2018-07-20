package org.amathon.ksr.application;

import java.io.File;

public interface S3Services {

  byte[] downloadFile(String keyName);

  void uploadFile(String keyName, File file);
}