package com.persipa.fs3.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.persipa.fs3.pojo.AuctionCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.persipa.fs3.pojo.Auction;
import com.persipa.fs3.pojo.Auctionrecord;
import com.persipa.fs3.pojo.Auctionuser;
import com.persipa.fs3.service.AuctionService;

@Controller
@RequestMapping("/auction")
public class AuctionController {

	private static final int PAGE_SIZE = 10;
	@Autowired
	private AuctionService auctionService;

	/**
	 * 查询拍品
	 * 
	 * @param query
	 * @param pageNum
	 * @return
	 */
	@RequestMapping("/findAuctions")
	public ModelAndView findAuctions(@ModelAttribute(value = "condition") Auction query,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum) {

		ModelAndView mv = new ModelAndView();
		PageHelper.startPage(pageNum, PAGE_SIZE);

		List<Auction> list = auctionService.findAuctions(query);
		PageInfo pageInfo = new PageInfo<>(list);

		mv.addObject("auctionList", list);
		mv.addObject("pageInfo", pageInfo);

		mv.setViewName("index");

		return mv;

	}

	/**
	 * 发布拍品
	 * 
	 * @param auction
	 * @param pic
	 * @param session
	 * @return
	 */
	@RequestMapping("/publishAuctions")
	public String publishAuctions(Auction auction, MultipartFile pic, HttpSession session) {

		// 处理文件上传
		try {
			String path = session.getServletContext().getRealPath("images");
			File targetFile = new File(path, pic.getOriginalFilename());

			pic.transferTo(targetFile);

			auction.setAuctionpic(pic.getOriginalFilename());
			auction.setAuctionpictype(pic.getContentType());
		} catch (IllegalStateException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();

		}

		auctionService.addAuction(auction);

		return "redirect:/auction/findAuctions";

	}

    /**
     * 跳转到修改拍品页面
     * @param auctionId
     * @return
     */
	@RequestMapping("/toUpdateDetail")
	public ModelAndView toUpdateDetail(int auctionId) {
		ModelAndView mv = new ModelAndView();

		Auction auction = auctionService.findAuctionByAuctionId(auctionId);

		mv.addObject("auction", auction);
		mv.setViewName("updateAuction");

		return mv;
	}

	/**
	 * 更新拍品
	 * 
	 * @param auction
	 * @param pic
	 * @param session
	 * @return
	 */
	@RequestMapping("/updateAuctoinSubmit")
	public String updateAuctoinSubmit(Auction auction, MultipartFile pic, HttpSession session) {
		try {
			String path = session.getServletContext().getRealPath("images");
			File targetFile = new File(path, pic.getOriginalFilename());

			pic.transferTo(targetFile);

			auction.setAuctionpic(pic.getOriginalFilename());
			auction.setAuctionpictype(pic.getContentType());
		} catch (IllegalStateException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();

		}

		auctionService.updateAuction(auction);
		return "redirect:/auction/findAuctions";
	}

	/**
	 * 删除拍品
	 * 
	 * @param auctionId
	 * @return
	 */
	@RequestMapping("/deleteAuction")
	public String deleteAuction(int auctionId) {

		auctionService.removeAuction(auctionId);

		return "redirect:/auction/findAuctions";

	}

	/**
	 * 跳转到商品详情页
	 * @param auctionId
	 * @return
	 */
	@RequestMapping("/toAuctionDetail/{auctionId}")
	public ModelAndView toAuctionDetail(@PathVariable int auctionId) {
		ModelAndView mv = new ModelAndView();
		Auction auction = auctionService.findAuctionAndRecordList(auctionId);
		System.out.println("-------------------------");
		System.out.println(auction);
		System.out.println("-------------------------");
		mv.addObject("auctionDetail", auction);
		mv.setViewName("auctionDetail");

		return mv;
	}

	/**
	 * 保存拍卖记录
	 * @param record
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("/saveAuctionRecord")
	public String saveAuctionRecord(Auctionrecord record, HttpSession session, Model model) {

		// 竞拍者id
		try {
				Auctionuser user = (Auctionuser) session.getAttribute("user");
				record.setUserid(user.getUserid());

				record.setAuctiontime(new Date());
				auctionService.addAuctionRecord(record);
		} catch (Exception e) {
			model.addAttribute("errorMsg", e.getMessage());
			System.out.println("---------------------------");
			System.out.println(e.getMessage());
			System.out.println("---------------------------");
			return "error";

		}

		return "redirect:/auction/toAuctionResult";

	}

    /**
     * 跳转到竞拍结果统计页面
     * @return
     */
	@RequestMapping("/toAuctionResult")
	public ModelAndView toAuctionResult(){
		System.out.println("in toAuctionResult method------------------------------------------------------");
		ModelAndView mv = new ModelAndView();
		List<AuctionCustom> endTimeList = auctionService.findAuctionEndTimeList();
		List<Auction> noEndTimeList = auctionService.findAuctionNoEndtimeList();

		mv.addObject("auctionCustomList",endTimeList);
		mv.addObject("auctionList",noEndTimeList);

		mv.setViewName("auctionResult");
		return mv;
	}
}
