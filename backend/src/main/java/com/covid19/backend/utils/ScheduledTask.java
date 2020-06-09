package com.covid19.backend.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 自动清理 storage 目录
 *
 * @author billchen
 * @version 1.0
 * @create 2020-03-15 00:20
 **/
@Component
@EnableScheduling
@Slf4j
public class ScheduledTask {

	private static final int CLEAN_THRESHOLD = 100;
	/**
	 * 每隔一小时自动清理本地超过 1000 个以上的最旧文件
	 */
	@Scheduled(fixedRate = 60 * 60 * 1000)
	public void CleanUpTask(){
		File file = new File(Utils.getAbsoluteRunnigPath(), "/temp");
		if(!file.exists() || file == null){
			return;
		}
		File[] fileList = new File(Utils.getAbsoluteRunnigPath(), "/temp").listFiles();
		Arrays.sort(fileList, new Comparator<File>() {
			@Override
			public int compare(File o1, File o2) {
				if (o1.lastModified() == o2.lastModified()) return 0;
				return o1.lastModified() > o2.lastModified() ? -1: 1;
			}
		});
		if(fileList.length >= CLEAN_THRESHOLD) {
			for(int i = CLEAN_THRESHOLD; i < fileList.length; i++){
				fileList[i].delete();
				log.info("Cleaning file: " + fileList[i].getName());
			}
		}


	}


}
