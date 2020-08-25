package kr.or.ddit.jsoup.category;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.jsoup.SqlMapClientFactory;

public class InsertCategory {
	
	static SqlMapClient smc = SqlMapClientFactory.getClient();
	
	public static void main(String[] args) throws IOException {
		
		Document doc = Jsoup.connect("https://www.gmarket.co.kr/?&jaehuid=200011415&gclid=EAIaIQobChMI76Po94%5Ft6gIVRLaWCh1UdgcWEAAYASAAEgKjrfD%5FBwE").get();
		String title = doc.title();
		
		System.out.println("홈페이지 제목 : " + title);
		
		System.out.println("======================================");
		
		Elements depth2 = doc.select(".link__2depth-item");
		
		List<CategoryVO> list = new ArrayList<>();
		for(int i = 0; i < depth2.size()/2; i++) {
			
			CategoryVO vo = new CategoryVO(depth2.get(i).parent().parent()
					.parent().parent().parent().parent().select(".link__1depth-item").text(),
			depth2.get(i).text()
			);
			list.add(vo);
			
//			System.out.println("부모 카테고리 : " + depth2.get(i).parent().parent()
//					.parent().parent().parent().parent().select(".link__1depth-item").text());
//			System.out.println("\t상세 카테고리 : " + depth2.get(i).text());
		}
		
		for(CategoryVO vo : list) {
			System.out.println(vo.toString());
			try {
				smc.insert("jsoup.insertCategory", vo);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("======================================");
		
		
	}
	
}
