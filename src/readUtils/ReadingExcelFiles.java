package readUtils;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;




//public class ReadingExcelFiles {
//	
//	private final Map<String, TagBean> tagBeansWordMap3 = new HashMap<String, TagBean>();
//	private final Set<TagBean> tagBeans3 = new HashSet<TagBean>();
//	private final Set<RelationshipBean> relationshipBeans3 = new HashSet<RelationshipBean>();
//	
//	private final Map<String, TagBean> tagBeansWordMap4 = new HashMap<String, TagBean>();
//	private final Set<TagBean> tagBeans4 = new HashSet<TagBean>();
//	private final Set<RelationshipBean> relationshipBeans4 = new HashSet<RelationshipBean>();
//	
//	public TagInput extractFromDBExcelFile(String excelPath) throws FileNotFoundException {
//		extractTagBeans(excelPath);
//		extractRelationships(excelPath);
//		TagInput tagInput = new TagInput(tagBeans,tagRelationshipBeans);
//		return tagInput;
//	}
//	
//	public TagInput extractFromOutputExcelFile(String excelPath) throws FileNotFoundException {
//		readingOutputNotepadTokenized(textfilepath);
//		TagInput tagInput2 = new TagInput(tagBeans2,tagRelationshipBeans2);
//		return tagInput2;
//	}
//	
//	
//	
//	private void extractTagBeans(String excelPath) {
//		try {
//
//			Xls_Reader xx2 = new Xls_Reader(excelPath);
//			int rowCount2 = xx2.getRowCount("listitems");
//			for (int i = ExcelConstants.rowNoAssociation; i <= rowCount2; i++) {
//				for (int j = ExcelConstants.colNo1; j <= ExcelConstants.colNo1; j++) {
//					String tagWord = xx2.getCellData("listitems", j, i);
//					if (tagWord.indexOf("null") == -1) {
//						String[] tag = tagWord.split(" ");
//						List<String> list = Arrays.asList(tag);
//						Set<String> swt = new HashSet<String>();
//						swt.addAll(list);
//						for (Iterator iterator = swt.iterator(); iterator.hasNext();) {
//							Object obj = iterator.next();
//							TagBean tagBean = new TagBean((String) obj);
//							tagBeans4.add(tagBean);
//							tagBeansWordMap4.put(tagWord, tagBean);
//						}
//					}
//				}
//			}
//		} 
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	
//	
//	private void extractRelationships(String path) {
//		String Sheetname = "listitems";
//		Xls_Reader Obj = new Xls_Reader(path);
//		int rowCount = Obj.getRowCount(Sheetname);
//		for (int i = ExcelConstants.rowNoName; i <= rowCount; i++) {
//			String startTagWord = Obj.getCellData("listitems", 0, i);
//			String relationShip = Obj.getCellData("listitems", 2, i);
//			RelationshipType relationShipType = RelationshipType.IS_PARENT;
//			if (relationShip.equalsIgnoreCase("no"))
//				relationShipType = RelationshipType.IS_CHILD;
//			String endTagWord = Obj.getCellData("listitems", 3, i);
//			TagBean tagBeanStart = tagBeansWordMap3.get(startTagWord);
//			TagBean tagBeanEnd = tagBeansWordMap3.get(endTagWord);
//			double relationshipWeight = 0;
//			RelationshipBean relationshipBean = new RelationshipBean(tagBeanStart, tagBeanEnd);
//			relationshipBeans3.add(relationshipBean);
//		}
//	}
//	
//	public static Set<String> createSetWithCommaSeperated(Set<String> set1) {
//		Set<String> swt = new HashSet<String>();
//		for (String string : set1) {
//			String[] tag = string.split(",");
//			List<String> list = Arrays.asList(tag);
//			swt.addAll(list);
//			for (Iterator<String> iterator = swt.iterator(); iterator.hasNext();) {
//			}
//		}
//		return swt;
//	}
//	
//	public static Set<String> createSetWithSpaceSeperated(Set<String> set1) {
//		Set<String> swt = new HashSet<String>();
//		for (String string : set1) {
//			String[] tag = string.split(" ");
//			List<String> list = Arrays.asList(tag);
//			swt.addAll(list);
//			for (Iterator<String> iterator = swt.iterator(); iterator.hasNext();) {
//			}
//		}
//		return swt;
//	}
//	
//	
//	
//
//}
