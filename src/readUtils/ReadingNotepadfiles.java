package readUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ReadingNotepadfiles {

	private final Map<String, TagBean> tagBeansWordMap = new HashMap<String, TagBean>();
	private final Set<TagBean> tagBeans = new HashSet<TagBean>();
	private final Set<RelationshipBean> tagRelationshipBeans = new HashSet<RelationshipBean>();

	private final Map<String, TagBean> tagBeansWordMap1 = new HashMap<String, TagBean>();
	private final Set<TagBean> tagBeans1 = new HashSet<TagBean>();
	private final Set<RelationshipBean> tagRelationshipBeans1 = new HashSet<RelationshipBean>();

	private final Map<String, TagBean> tagBeansWordMap2 = new HashMap<String, TagBean>();
	private final Set<TagBean> tagBeans2 = new HashSet<TagBean>();
	private final Set<RelationshipBean> tagRelationshipBeans2 = new HashSet<RelationshipBean>();

	private final Map<String, TagBean> tagBeansWordMap3 = new HashMap<String, TagBean>();
	private final Set<TagBean> tagBeans3 = new HashSet<TagBean>();
	private final Set<RelationshipBean> tagRelationshipBeans3 = new HashSet<RelationshipBean>();
    /**
     * @author Pratap 
     *  :Internally this Method Calls Reading Notepad File Method
     * @param textfilepath
     * @param tokenize 
     * tokenize as true or false based on requirment
     * @return
     * @throws FileNotFoundException
     *  
     */
	public TagInput extractFromDBNotepad(String textfilepath, boolean tokenize) throws FileNotFoundException {
		readingDBNotepad(textfilepath, tokenize);
		TagInput tagInput = new TagInput(tagBeans, tagRelationshipBeans);
		return tagInput;
	}
   /**
    * @author Pratap
    * : Internally this Method Calls Reading Output(Algorithm Output )Notepad file
    * @param textfilepath
    * @return
    * @throws FileNotFoundException
    */
	public TagInput extratFromOutputNotepad(String textfilepath) throws FileNotFoundException {
		readingOutputNotepad(textfilepath);
		TagInput tagInput1 = new TagInput(tagBeans1, tagRelationshipBeans1);
		return tagInput1;
	}

	public TagInput extractFromDBNotepadTokenized(String textfilepath) throws FileNotFoundException {
		readingDBNotepadTokenized(textfilepath);
		TagInput tagInput2 = new TagInput(tagBeans2, tagRelationshipBeans2);
		return tagInput2;
	}

	public TagInput extratFromOutputNotepad23rd(String textfilepath, int Tokenize) throws FileNotFoundException {
		readingOutputNotepadOutput23rdJune(textfilepath, 0);
		TagInput tagInput3 = new TagInput(tagBeans3, tagRelationshipBeans3);
		return tagInput3;
	}

	public void readingDBNotepad1(String textfilepath) throws FileNotFoundException {

		FileReader fileReader = new FileReader(textfilepath);
		try (BufferedReader br = new BufferedReader(fileReader)) {
			String sCurrentLine;
			TagBean startTagBean = null;
			TagBean endTagBean = null;
			while ((sCurrentLine = br.readLine()) != null) {
				String[] Relations = sCurrentLine.split(";");
				String tagWord1 = Relations[0].trim().replace("\" ", "");
				String startTagWord = tagWord1.trim();
				startTagBean = new TagBean(startTagWord);
				tagBeans.add(startTagBean);
				String tagWord2 = "";
				tagWord2 = Relations[1].trim();
				String endTagWord = tagWord2.trim().replace("\"", "");
				endTagBean = new TagBean(endTagWord);
				tagBeans.add(endTagBean);
				RelationshipBean relationshipBean = new RelationshipBean(startTagBean, endTagBean);
				tagRelationshipBeans.add(relationshipBean);

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(Thread.currentThread().getStackTrace()[2].getLineNumber());
		}
	}

	public void readingOutputNotepad(String textfilepath) throws FileNotFoundException {

		FileReader fileReader = new FileReader(textfilepath);
		try (BufferedReader br = new BufferedReader(fileReader)) {
			String sCurrentLine;
			int i = 0;
			TagBean startTagBean = null;
			while ((sCurrentLine = br.readLine()) != null) {
				if (i % 2 != 0) {
					String[] allRelations = sCurrentLine.split(";");
					TagBean endTagBean = null;
					for (String relation : allRelations) {
						String[] relationAttributes = relation.split(",");
						String endTagWord = relationAttributes[0].trim().substring(1,
								relationAttributes[0].trim().length());
						endTagBean = new TagBean(endTagWord);
						tagBeans1.add(endTagBean);
						RelationshipBean relationshipBean = new RelationshipBean(startTagBean, endTagBean);
						tagRelationshipBeans1.add(relationshipBean);
					}
				} else {
					startTagBean = new TagBean(sCurrentLine.trim());
					tagBeans1.add(startTagBean);
				}
				i++;
			}
		} catch (IOException e) {
			System.out.println("Got Exception " + e);
		}

		System.out.println("Tags Beans size of Output Notepad :" + tagBeans1.size());
	}

	public void readingDBNotepadTokenized(String textfilepath) throws FileNotFoundException {

		FileReader fileReader = new FileReader(textfilepath);
		try (BufferedReader br = new BufferedReader(fileReader)) {
			String sCurrentLine;
			TagBean startTagBean = null;
			TagBean endTagBean = null;
			while ((sCurrentLine = br.readLine()) != null) {
				String[] Relations = sCurrentLine.split(";");
				String tagWordPhrase = Relations[0].trim().replace('\"', ' ').trim();
				String[] tagWordPhraseArray = tagWordPhrase.split(" ");
				for (String tagToken : tagWordPhraseArray) {
					startTagBean = new TagBean(tagToken);
					tagBeans2.add(startTagBean);
				}
				String tagWordChildPhrase = Relations[1].trim().replace("\"", "").replace("\"", "").trim();
				String[] tagWordChildPhraseArray = tagWordChildPhrase.split(" ");
				for (String tagToken : tagWordChildPhraseArray) {
					endTagBean = new TagBean(tagToken);
					tagBeans2.add(endTagBean);
				}

			}
			RelationshipBean relationshipBean = new RelationshipBean(startTagBean, endTagBean);
			tagRelationshipBeans2.add(relationshipBean);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(Thread.currentThread().getStackTrace()[2].getLineNumber());
		}

		System.out.println("TagBeans2 size of readingDBNotepadTokenized " + tagBeans2.size());
		new TagInput(tagBeans2, null);

	}

	public void readingOutputNotepadOutput23rdJune(String textfilepath, int Tokenize) throws FileNotFoundException {

		if (Tokenize == 1) {
			FileReader fileReader = new FileReader(textfilepath);
			try (BufferedReader br = new BufferedReader(fileReader)) {
				String sCurrentLine;
				TagBean tagword = null;
				while ((sCurrentLine = br.readLine()) != null) {
					String[] Relations = sCurrentLine.split(" ");
					for (int i = 0; i < Relations.length; i++) {
						String tagWordPhrase = Relations[i].trim().trim();
						tagword = new TagBean(tagWordPhrase);
						tagBeans3.add(tagword);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(Thread.currentThread().getStackTrace()[2].getLineNumber());
			}
			System.out.println("TagBeans size of readingOutputNotepadTokenized_Output23rdJune " + tagBeans3.size());
			new TagInput(tagBeans3, null);
		} else if (Tokenize == 0) {
			FileReader fileReader = new FileReader(textfilepath);
			try (BufferedReader br = new BufferedReader(fileReader)) {
				String sCurrentLine;
				TagBean tagword = null;
				while ((sCurrentLine = br.readLine()) != null) {
					tagword = new TagBean(sCurrentLine);
					tagBeans3.add(tagword);
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(Thread.currentThread().getStackTrace()[2].getLineNumber());
			}
			System.out.println("TagBeans size of readingOutputNotepad_Output23rdJune " + tagBeans3.size());
			new TagInput(tagBeans3, null);
		}
	}

	/**
	 * @author Pratap Bandagi Method Reads Notepad file having Tags extracted
	 *         from Database
	 * @param textfilepath
	 * @param Tokenize
	 *            if Tokenize = 1 then sentences will be split into phrases if
	 *            Tokenize = 0 then whole sentence will be considered as tags
	 */
	public void readingDBNotepad(String textfilepath, boolean tokenize) throws FileNotFoundException {

		if (!tokenize) {
			System.out.println("Tokenize is false, treating all as phrases");
			FileReader fileReader = new FileReader(textfilepath);
			try (BufferedReader br = new BufferedReader(fileReader)) {
				String sCurrentLine;
				TagBean startTagBean = null;
				TagBean endTagBean = null;
				while ((sCurrentLine = br.readLine()) != null) {
					String[] Relations = sCurrentLine.split(";");
					String tagWord1 = Relations[0].trim().replace("\"", "").trim();
					String startTagWord = tagWord1.trim();
					startTagBean = new TagBean(startTagWord);
					tagBeans.add(startTagBean);
					String tagWord2 = "";
					tagWord2 = Relations[1].trim();
					String endTagWord = tagWord2.trim().replace("\"", "").trim();
					endTagBean = new TagBean(endTagWord);
					tagBeans.add(endTagBean);
					RelationshipBean relationshipBean = new RelationshipBean(startTagBean, endTagBean);
					tagRelationshipBeans.add(relationshipBean);

				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(Thread.currentThread().getStackTrace()[2].getLineNumber());
			}
			//System.out.println("TagBeans Size :"+tagBeans);
			System.out.println("Tags Beans size of DB Notepad :" + tagBeans.size());
		} else if (tokenize) {
			System.out.println("Tokenize is true, tokenizing as single words");
			FileReader fileReader = new FileReader(textfilepath);
			try (BufferedReader br = new BufferedReader(fileReader)) {
				String sCurrentLine;
				TagBean startTagBean = null;
				TagBean endTagBean = null;
				while ((sCurrentLine = br.readLine()) != null) {
					String[] Relations = sCurrentLine.split(";");
					String tagWordPhrase = Relations[0].trim().replace("\"", "").trim();
					String[] tagWordPhraseArray = tagWordPhrase.split(" ");
					for (String tagToken : tagWordPhraseArray) {
						startTagBean = new TagBean(tagToken.trim());
						tagBeans.add(startTagBean);
					}
					String tagWordChildPhrase = Relations[1].trim().replace("\"", "").trim();
					String[] tagWordChildPhraseArray = tagWordChildPhrase.split(" ");
					for (String tagToken : tagWordChildPhraseArray) {
						endTagBean = new TagBean(tagToken.trim());
						tagBeans.add(endTagBean);
					}
					
					
					RelationshipBean relationshipBean = new RelationshipBean(startTagBean, endTagBean);
					tagRelationshipBeans.add(relationshipBean);
				}

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(Thread.currentThread().getStackTrace()[2].getLineNumber());
			}
			//System.out.println("Tag beans size "+tagBeans);
			System.out.println("Tag beans size "+tagBeans.size());
			System.out.println("TagBeans size of readingDBNotepad " + tagBeans.size());
			new TagInput(tagBeans, tagRelationshipBeans);

		}

	}

}
