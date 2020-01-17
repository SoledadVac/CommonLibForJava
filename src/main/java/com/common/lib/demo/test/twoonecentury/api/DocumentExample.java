package com.common.lib.demo.test.twoonecentury.api;

import java.util.List;


import com.common.lib.demo.test.twoonecentury.api.constant.APIConstant;
import com.common.lib.demo.test.twoonecentury.api.model.common.*;
import com.common.lib.demo.test.twoonecentury.api.model.document.Document;
import com.common.lib.demo.test.twoonecentury.api.model.document.Preview;
import com.common.lib.demo.test.twoonecentury.api.service.APIDocumentService;
import com.common.lib.demo.test.twoonecentury.api.service.APIRequestParams;
import com.common.lib.demo.test.twoonecentury.api.util.APIResult;
import com.google.gson.Gson;

/**
 * 文档资源列表
 * 
 * @author zhoushubing
 *
 */
public class DocumentExample {

	public static void main(String[] args) {
		APIDocumentService service = new APIDocumentService();
		// 获取省份地区信息
		List<Province> provinces = service.getProvinces();
		System.out.println(new Gson().toJson(provinces));
		// 获取小学教材科目
		List<Subject> subjects = service.getSubjects(APIConstant.STAGE_XIAOXUE);
		System.out.println(new Gson().toJson(subjects));
		System.out.println(subjects.size());
		// 获取小学第一个科目下的教材版本列表
		List<Version> versions = service.getVersions(APIConstant.STAGE_XIAOXUE, subjects.get(0).getSubjectId());
		System.out.println("versions size:" + versions.size());
		System.out.println(new Gson().toJson(versions));
		// 获取版本教材下的册别信息列表
		List<Book> books = service.getBooks(versions.get(2).getVersionId());
		System.out.println("books size:" + books.size());
		System.out.println(new Gson().toJson(books));

		// 此处可获取Book下所有章节目录树
		List<Chapter> chapters = service.getChapters(books.get(1).getBookId());
		System.out.println("chapters size:" + chapters.size());
		System.out.println(new Gson().toJson(chapters));

		// System.exit(0);
		List<KnowledgePoint> knowledges = service.getKnowledgePoints(APIConstant.STAGE_XIAOXUE, 2);
		System.out.println(new Gson().toJson(knowledges));

		// 根据章节获取文档
		APIResult<Document> documents = service.getDocuments(new APIRequestParams() {
			{
				this.stage = APIConstant.STAGE_XIAOXUE;
				this.subjectId = subjects.get(0).getSubjectId();
				this.chapterId = chapters.get(0).getId();
				this.page = 1;
			}
		});
		System.out.println(new Gson().toJson(documents.getPage()));
		System.out.println(new Gson().toJson(documents.getData()));
		System.out.println(service.getDocumentDownurl(documents.getData().get(0).getItemId()));
		
		//4033564L
		List<Preview> preview = service.getPreview(documents.getData().get(0).getItemId());
		System.out.println(new Gson().toJson(preview));

		// 根据知识点获取文档
		documents = service.getDocuments(new APIRequestParams() {
			{
				this.stage = APIConstant.STAGE_XIAOXUE;
				this.subjectId = subjects.get(0).getSubjectId();
				this.knowledgeId = knowledges.get(0).getId();
				this.page = 2;
			}
		});
		System.out.println(new Gson().toJson(documents.getPage()));
		System.out.println(new Gson().toJson(documents.getData()));
		
		//根据标题搜索
		documents = service.getDocuments(new APIRequestParams(){
			{
				this.stage = APIConstant.STAGE_XIAOXUE;
				this.subjectId = subjects.get(0).getSubjectId();
				this.title = "测试";
				this.knowledgeId = knowledges.get(0).getId();
				this.page = 1;
			}
		});
		System.out.println(new Gson().toJson(documents.getData()));
	}
}
