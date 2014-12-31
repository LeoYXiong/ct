package com.ibm.issac.toolkit.file;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import com.ibm.issac.toolkit.Cube;
import com.ibm.issac.toolkit.DatetimeUtility;
import com.ibm.issac.toolkit.logging.ColorLog;

/**
 * �����ļ�����
 * 
 * @author issac
 * 
 */
public final class FileUtility {
	/**
	 * <p>
	 * Creates an InputStream from a file, and fills it with the complete file. Thus, available() on the returned InputStream will return the full number of bytes the file contains
	 * </p>
	 * 
	 * @param fname
	 *            The filename
	 * @return The filled InputStream
	 * @exception IOException
	 *                , if the Streams couldn't be created.
	 **/
	public static InputStream fullStream(String fname) throws IOException {
		FileInputStream fis = new FileInputStream(fname);
		DataInputStream dis = new DataInputStream(fis);
		byte[] bytes = new byte[dis.available()];
		dis.readFully(bytes);
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		return bais;
	}

	/**
	 * Based on an old file name, prompt up with a new one.
	 * 
	 * @param oldName
	 */
	public static String promptNewName(String oldName) {
		return oldName + Cube.produceRandomLong();
	}

	/**
	 * normalize separtors and formats for a path presentation. This method works for both file and directory.
	 * 
	 * @param pathStr
	 * @return
	 */
	public static String normalizePathString(String pathStr) {
		String normalizedStr = pathStr;
		normalizedStr = normalizedStr.replaceAll("\\\\", "/");
		// normalizedStr = normalizedStr.replaceAll("\\", "/");
		return normalizedStr;
	}

	/**
	 * ��ָ��Ŀ¼����һ����ʱ�ļ�����Ҫ�ṩ�����ļ������ڵ���������·����
	 * 
	 * @param absolutePath
	 * @return
	 */
	public static File createTempFile(String absolutePath) {
		final File file = new File(absolutePath);
		file.deleteOnExit();// ��ΪSTREAMû�йرյ�ԭ�򣬸÷����п�����Ч
		return file;
	}


	/**
	 * ת���ļ�Ŀ¼
	 * 
	 * @param filename
	 *            �ļ���
	 * @param oldpath
	 *            ��Ŀ¼
	 * @param newpath
	 *            ��Ŀ¼
	 * @param cover
	 *            ����Ŀ¼�´��ں�ת���ļ�������ͬ�ļ������ļ�ʱ���Ƿ񸲸���Ŀ¼���ļ���cover=true���Ḳ��ԭ�ļ������򲻲���
	 */

	public static void move(String filename, String oldpath, String newpath, boolean cover) {
		if (!oldpath.equals(newpath)) {
			File oldfile = new File(oldpath + "/" + filename);
			File newfile = new File(newpath + "/" + filename + "." + DatetimeUtility.formatDate("yyyyMMddHHmmss", new Date()));
			if (newfile.exists()) {// ���ڴ�ת��Ŀ¼�£��Ѿ����ڴ�ת���ļ�
				if (cover)// ����
					oldfile.renameTo(newfile);
				else
					ColorLog.warn("����Ŀ¼���Ѿ����ڣ�" + filename);
			} else {
				oldfile.renameTo(newfile);
			}
		}
	}

	/**
	 * ��ѯһ���ı��ļ��ж�����
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static long countRow(String filename) throws IOException {
		InputStream is = new BufferedInputStream(new FileInputStream(filename));
		try {
			byte[] c = new byte[1024];
			long count = 0;
			int readChars = 0;
			while ((readChars = is.read(c)) != -1) {
				for (int i = 0; i < readChars; ++i) {
					if (c[i] == '\n')
						++count;
				}
			}
			return count;
		} finally {
			is.close();
		}
	}

}
