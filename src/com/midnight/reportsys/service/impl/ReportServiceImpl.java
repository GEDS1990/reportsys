package com.midnight.reportsys.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.midnight.reportsys.dto.ReportDTO;
import com.midnight.reportsys.mapper.NoticeMapper;
import com.midnight.reportsys.mapper.ReportMapper;
import com.midnight.reportsys.pojo.Notice;
import com.midnight.reportsys.pojo.Report;
import com.midnight.reportsys.service.ReportService;
import com.midnight.reportsys.util.ValidateUtil;

@Service("reportService")
public class ReportServiceImpl implements ReportService {
	@Autowired
	private ReportMapper reportMapper;
	@Autowired
	private NoticeMapper noticeMapper;

	// 保存报表信息
	public void saveReport(Report report) throws Exception {
		reportMapper.saveReport(report);
	}

	/**
	 *
	 * @param uploadfile
	 *            文件上传
	 * @return 返回文件的新名称
	 * @throws Exception
	 */
	public String saveFileupload(MultipartFile uploadfile, String type) throws Exception {
		// 将上传的文件保存到E:\develop\reportsys
		String filePath = null;
		if (type.equals("daily")) {
			filePath = "E:\\develop\\reportsys\\dailyreport\\";
		}
		if (type.equals("weekly")) {
			filePath = "E:\\develop\\reportsys\\weeklyreport\\";
		}
		if (type.equals("template")) {
			filePath = "E:\\develop\\reportsys\\template\\";
		}

		// 获取上传文件名称
		String originalFileName = uploadfile.getOriginalFilename();
		String newFileName = null;

		if (ValidateUtil.isValid(originalFileName)) {
			// 生成一个随机的文件名称
			newFileName = UUID.randomUUID() + originalFileName.substring(originalFileName.lastIndexOf("."));

			// 生成新文件
			File file = new File(filePath + newFileName);
			// 将文件写入到磁盘
			uploadfile.transferTo(file);
		}

		return newFileName;
	}

	// 获取报表列表
	public List<ReportDTO> getReportList(int intPage, int number, String type, int userId) throws Exception {
		if (type.equals("daily") || type.equals("weekly")) {
			// 获取个人报表
			// 利用mybatis插件 实现分页，作用于dao层，不要把两者分开，两者之间不加其他语句
			PageHelper.startPage(intPage, number);
			List<Report> reports = reportMapper.getReportList(type, userId);
			
			return reportToReportDTO(reports);
		}
		if (type.equals("allWeekly")) {
			// 获取所有周报表
			type = "weekly";
			PageHelper.startPage(intPage, number);
			List<Report> reports = reportMapper.getAllReportList(type);
			return reportToReportDTO(reports);
		}
		if (type.equals("allDaily")) {
			// 获取所有日报表
			type = "daily";
			PageHelper.startPage(intPage, number);
			List<Report> reports = reportMapper.getAllReportList(type);
			return reportToReportDTO(reports);
		}
		if (type.equals("template")) {
			// 获取所有模板
			type = "template";
			List<Report> reports = reportMapper.getAllReportList(type);
			return reportToReportDTO(reports);
		}

		// 其他情况属于 根据用户名进行模糊查询
		String name = "'%" + type + "%'";
		PageHelper.startPage(intPage, number);
		List<Report> reports = reportMapper.getReportListByName(name);
		return reportToReportDTO(reports);
	}

	// 获取报表数量
	public int getReportCount(String type, int userId) throws Exception {
		int count = 0;

		switch (type) {
		case "daily":
			// 获取个人日报表
			count = reportMapper.getReportCount(type, userId);
			break;

		case "weekly":
			// 获取个人周报表
			count = reportMapper.getReportCount(type, userId);
			break;

		case "allWeekly":
			// 获取所有周报表
			type = "weekly";
			count = reportMapper.getAllReportCount(type);
			break;

		case "allDaily":
			// 获取所有日报表
			type = "daily";
			count = reportMapper.getAllReportCount(type);
			break;

		case "template":
			// 获取所有日报表
			type = "template";
			count = reportMapper.getAllReportCount(type);
			break;

		default:
			// 其他情况属于 根据用户名进行模糊查询
			String name = "'%" + type + "%'";
			count = reportMapper.getCountReportListByName(name);
			break;
		}
		return count;
	}

	// 根据id获取report对象
	public Report getReportById(String id) throws Exception {
		return reportMapper.getReportById(id);
	}

	// 根据id删除报表,并删除文件
	public void deleteReportById(String id) throws Exception {
		Report report = reportMapper.getReportById(id);
		String type = report.getType();
		String url = report.getUrl();
		String filePath = null;
		if (type.equals("daily")) {
			filePath = "E:\\develop\\reportsys\\dailyreport\\" + url;
		}
		if (type.equals("weekly")) {
			filePath = "E:\\develop\\reportsys\\weeklyreport\\" + url;
		}
		File file = new File(filePath);
		if (file.exists()) {
			file.delete();
		}
		reportMapper.deleteReportById(id);
	}

	// 获取指定类型报表截止时间
	public Date getReportDeadline(String type) throws Exception {
		if (type.equals("template"))
			return null;
		Notice notice = noticeMapper.findNotice(type);

		String s = notice.getContent().trim();
		String s1 = s.substring(0, 10);
		String s2 = s.substring(23, 31);
		s = s1 + " " + s2;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = sdf.parse(s);
		return d;
	}

	// 统计日或周报表的数量
	public int getDailyOrWeeklyCount(String type, String time) throws Exception {
		return reportMapper.getDailyOrWeeklyCount(type, time);
	}

	// 根据用户id 和 类型 获取报表集合
	public List<Report> findReportListByUserIdAndType(int userId, String type) throws Exception {
		return reportMapper.getReportList(type, userId);
	}

	/**
	 * 将Report转换为ReportDTO
	 * @param reports
	 * @return
	 */
	public List<ReportDTO> reportToReportDTO(List<Report> reports) {
		List<ReportDTO> reportDTOs = new ArrayList<>();
		for (Report report : reports) {
			ReportDTO rDTO = new ReportDTO();
			rDTO.setId(report.getId());
			rDTO.setName(report.getName());
			rDTO.setCreateTime(report.getCreateTime());
			rDTO.setDownloadUrl(report.getDownloadUrl());
			String preview = "http://ow365.cn/?i=11854&furl=http://120.77.202.142/report/downloadReport/";
			if (report.getType().equals("daily")) {
				preview += "daily?id=" + report.getId();
			} else if (report.getType().equals("weekly")) {
				preview += "weekly?id=" + report.getId();
			} else {
				preview += "template?id=" + report.getId();
			}
			String apreview ="<a href=\""+preview+"\">查看</a>";
			rDTO.setPreview(apreview);

			reportDTOs.add(rDTO);
		}
		
		return reportDTOs;
	}
}
