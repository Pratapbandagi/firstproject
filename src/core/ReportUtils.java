package core;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import readUtils.TagBean;
import readUtils.TagInput;

public class ReportUtils {

	public static void findCommonTags(TagInput tagInputA, TagInput tagInputB) {
		double sizeofset1 = tagInputA.getTagBeans().size();
		double sizeofset2 = tagInputB.getTagBeans().size();
		int count = 0;
		Set<TagBean> commonTags = new HashSet<TagBean>();
		for (TagBean firstRecord : tagInputA.getTagBeans()) {
			for (TagBean secondRecord : tagInputB.getTagBeans()) {
				if (firstRecord.getWord().equalsIgnoreCase(secondRecord.getWord())) {
					count++;
					commonTags.add(firstRecord);
				}
			}
		}
		System.out.println("Size of Common Tags : "+commonTags.size());
		System.out.println("Total Common elements in both the Sets are : " + count);
		double percantageOfCommonWordsWithset1 = (count / sizeofset1 * 100);
		double percantageOfCommonWordsWithset2 = (count / sizeofset2 * 100);
		System.out.println("Percentage  of Common words with Respect to Tag DataBase : " + percantageOfCommonWordsWithset1	+ "%");
		System.out.println("Percentage of Common words with Respect to OutPutFile : " + percantageOfCommonWordsWithset2	+ "%");
		//System.out.println("Common Tags : " + commonTags);
	}

	public static void findCommonRelationships(TagInput tagInputA, TagInput tagInputB) {
		
		System.out.println("*****************************************************************************************************************");
		double sizeOftagInputB = 0;
		double sizeOftagInputA = tagInputA.getRelationshipBeans().size();
		try{
			
			sizeOftagInputB = tagInputB.getRelationshipBeans().size();
			
		}catch(Exception e){
			e.getMessage();
		}
		
		int count = 0;
		System.out.println("*********************************************************************************************************");
		System.out.println("Before Intersection : " + tagInputB.getRelationshipBeans().size());
		tagInputB.getRelationshipBeans().retainAll(tagInputA.getRelationshipBeans());
		System.out.println("After Intersection " + tagInputB.getRelationshipBeans().size());
		count = tagInputB.getRelationshipBeans().size();
		double percantageOfCommonWordsWithset1 = (count / sizeOftagInputA * 100);
		double percantageOfCommonWordsWithset2 = (count / sizeOftagInputB * 100);
		System.out.println("Percentage  of Common words with Respect to Uthkarsh : " + percantageOfCommonWordsWithset1	+ "%");
		System.out.println("Percentage of Common words with Respect to DB Records : " + percantageOfCommonWordsWithset2	+ "%");
		
	}
	
	
	public static Set<String> findCommonElementsInSet(Set<String> set1, Set<String> set2) {

		double sizeofset1 = set1.size();
		double sizeofset2 = set2.size();

		int count = 0;
		Set<String> tmp = new HashSet<String>();
		for (String firstRecord : set1) {
			for (String secondRecord : set2) {
				if (firstRecord.equalsIgnoreCase(secondRecord)) {
					count++;

					tmp.add(firstRecord);
				}
			}
		}
		System.out.println("Total Common elements in both the Sets are : " + count);
		double percantageOfCommonWordsWithset1 = (count / sizeofset1 * 100);
		double percantageOfCommonWordsWithset2 = (count / sizeofset2 * 100);
		System.out.println("Percentage  of Common words with Respect to Set 1 : " + percantageOfCommonWordsWithset1
				+ "%");
		System.out.println("Percentage of Common words with Respect to Set 2 : " + percantageOfCommonWordsWithset2
				+ "%");
		return tmp;
	}
	
	
	public static void findCommonTagsWithContains(TagInput tagInputA, TagInput tagInputB) {
		double sizeofset1 = tagInputA.getTagBeans().size();
		double sizeofset2 = tagInputB.getTagBeans().size();
		int count = 0;
		Set<TagBean> commonTags = new HashSet<TagBean>();
		
			for (TagBean secondRecord : tagInputB.getTagBeans()) {
				for (TagBean firstRecord : tagInputA.getTagBeans()) {
					
					String large = firstRecord.getWord();
					String small = secondRecord.getWord();
					
					if(secondRecord.getWord().length() > firstRecord.getWord().length() ){
						large = secondRecord.getWord();
						small = firstRecord.getWord();
					}
					
						
				if (large.contains(small)) {
					  count++;
					  commonTags.add(secondRecord);
				}
			}
		}
		System.out.println("Size of Common Tags : "+commonTags.size());
		System.out.println("Total Common elements in both the Sets are : " + count);
		double percantageOfCommonWordsWithset1 = (count / sizeofset1 * 100);
		double percantageOfCommonWordsWithset2 = (count / sizeofset2 * 100);
		System.out.println("Percentage  of Common words with Respect to Tag DataBase : " + percantageOfCommonWordsWithset1	+ "%");
		System.out.println("Percentage of Common words with Respect to OutPutFile : " + percantageOfCommonWordsWithset2	+ "%");
		//System.out.println("Common Tags : " + commonTags);
	}


}
