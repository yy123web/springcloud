package com.zzj.springboot.util;

import java.io.*;
import java.util.List;
import java.util.zip.GZIPInputStream;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.GZIPInputStream;

/**
 * @author zhaozj37918
 * @date 2021年12月02日 11:27
 */
@Slf4j
public class UnFileUtil {
    /**
     * Tar文件解压方法
     *
     * @param tarGzFile 要解压的压缩文件名称（绝对路径名称）
     * @param destDir   解压后文件放置的路径名（绝对路径名称）当路径不存在，会自动创建
     * @return 解压出的文件列表
     */
    public static List<String> deCompressGZipFile(String tarGzFile, String destDir, List<String> fileNames) throws Exception {

        // 建立输出流，用于将从压缩文件中读出的文件流写入到磁盘
        TarArchiveEntry entry = null;
        TarArchiveEntry[] subEntries = null;
        File subEntryFile = null;
        // 存储tar包下所有文件名
        FileInputStream fis = null;
        GZIPInputStream gis = null;
        try {
            fis = new FileInputStream(FileUtils.getFile(tarGzFile));
            gis = new GZIPInputStream(fis);
            TarArchiveInputStream taris = new TarArchiveInputStream(gis);
            while ((entry = taris.getNextTarEntry()) != null) {
                StringBuilder entryFileName = new StringBuilder();
                entryFileName.append(destDir).append(File.separator).append(entry.getName());
                fileNames.add(entry.getName());
                File entryFile = FileUtils.getFile(entryFileName.toString());
                if (entry.isDirectory()) {
                    if (!entryFile.exists()) {
                        entryFile.mkdir();
                    }
                    subEntries = entry.getDirectoryEntries();
                    for (int i = 0; i < subEntries.length; i++) {
                        OutputStream out = null;
                        try {
                            out = new FileOutputStream(subEntryFile);
                            subEntryFile = FileUtils.getFile(entryFileName + File.separator + subEntries[i].getName());
                            org.apache.commons.io.IOUtils.copy(taris, out);
                        } catch (Exception e) {
                            log.error("解压失败，文件名" + subEntries[i].getName(), e.getMessage());
                            log.error("", e);
                        } finally {
                            if (null != out) {
                                try {
                                    out.close();
                                } catch (IOException e) {
                                    log.error("文件流关闭异常", e);
                                }
                            }
                        }
                    }
                } else {
                    checkFileExists(entryFile);
                    OutputStream out = null;
                    try {
                        out = new FileOutputStream(entryFile);
                        IOUtils.copy(taris, out);
                        //如果是gz文件进行递归解压
                        if (entryFile.getName().endsWith(".gz")) {
                            deCompressGZipFile(entryFile.getPath(), destDir, fileNames);
                        }
                    } catch (Exception e) {
                        log.error("解压失败", e);
                        log.error("", e);
                    } finally {
                        if (null != out) {
                            try {
                                out.close();
                            } catch (IOException e) {
                                log.error("文件流关闭异常", e);
                            }
                        }
                    }



                }
            }
        } catch (Exception e) {
            log.error("失败",e);
        } finally {
            if (null != gis) {
                try {
                    gis.close();
                } catch (IOException e) {
                    log.error("文件流关闭异常", e);
                }
            }
            if (null != fis) {
                try {
                    fis.close();
                } catch (IOException e) {
                    log.error("文件流关闭异常", e);
                }
            }
        }
        log.info("解压成功");
        return fileNames;
    }

    public static void checkFileExists(File file) {
        //判断是否是目录
        if (file.isDirectory()) {
            if (!file.exists()) {
                file.mkdir();
            }
        } else {
            //判断父目录是否存在，如果不存在，则创建
            if (file.getParentFile() != null && !file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                log.error("文件创建失败:" + e);
            }
        }
    }
}
