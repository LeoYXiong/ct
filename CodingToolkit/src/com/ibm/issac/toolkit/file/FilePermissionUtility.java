package com.ibm.issac.toolkit.file;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;

import com.ibm.issac.toolkit.logging.ColorLog;

/**
 * �ļ�Ȩ�޿��Ʋ���������
 * 
 * @author issac
 * 
 */
public final class FilePermissionUtility {
	/**
	 * Ϊ�ļ���SHARED���������������̶�ȡ���������÷�����OS�йأ�����ЩOS����Ч��
	 * 
	 * @param file
	 *            �ļ�������������Ŀ¼��Ч
	 * @return
	 * @throws IOException
	 */
	public static FileLock createFileLock(File file) throws IOException, OverlappingFileLockException {
		FileChannel fc = new RandomAccessFile(file, "rw").getChannel();
		FileLock lock = fc.tryLock();
		if (lock == null)
			ColorLog.warn("Unable to acquire lock for file " + file.getAbsolutePath());
		FilePermissionUtility.tryWritingToFileChannel(fc);
		return lock;
	}

	/**
	 * ��ʾ�ļ���ǰȨ��
	 * 
	 * @param file
	 */
	public static void displayFilePermission(File file) {
		ColorLog.debug("Can write? " + file.canWrite());
		ColorLog.debug("Can read? " + file.canRead());
	}

	/**
	 * �����ڲ��Եķ��������Ը�һ���ļ���д��һЩ���ݡ�
	 * 
	 * @param fc
	 */
	public static void tryWritingToFileChannel(FileChannel fc) {
		// First, we need a buffer to hold the timestamp
		ByteBuffer bytes = ByteBuffer.allocate(8); // a long is 8 bytes
		// Put the time in the buffer and flip to prepare for writing
		// Note that many Buffer methods can be "chained" like this.
		bytes.putLong(System.currentTimeMillis() + 10000).flip();
		try {
			fc.write(bytes);// Write the buffer contents to the channel
			fc.force(false); // Force them out to the disk
			ColorLog.debug("File channel wrote to the file successfully.");
		} catch (IOException e) {
			ColorLog.warn("File Channel failed to write to the file.");
			e.printStackTrace();
		}

	}
}
