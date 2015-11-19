package me.cullycross.valerie.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import timber.log.Timber;

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 11/20/15
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */
public class FileUtils {

  public static void write(@NonNull Context ctx, @NonNull String fileName,
      @NonNull String content) {
    File file;
    FileOutputStream outputStream;
    try {
      file = new File(ctx.getCacheDir(), fileName);

      outputStream = new FileOutputStream(file);
      outputStream.write(content.getBytes());
      outputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static String read(@NonNull Context ctx, @NonNull String fileName) {
    BufferedReader input;
    File file;
    StringBuilder builder = new StringBuilder("");
    try {
      file = new File(ctx.getCacheDir(), fileName);

      input = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
      String line;
      while ((line = input.readLine()) != null) {
        builder.append(line);
      }

      Timber.d(builder.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }

    return builder.toString();
  }
}
