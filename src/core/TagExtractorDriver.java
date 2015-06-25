package core;

import java.io.FileNotFoundException;

import readUtils.ReadingNotepadfiles;
import readUtils.TagInput;

public class TagExtractorDriver {

	public static void main(String[] args) throws FileNotFoundException {

		ReadingNotepadfiles Obj_Notepadfile = new ReadingNotepadfiles();
		TagInput tokenziedTagInputDatabase = Obj_Notepadfile.extractFromDBNotepad("E:\\excelfiles\\New folder\\DBRecordsfile.txt", false);
		TagInput Obj_outputitems = Obj_Notepadfile.extratFromOutputNotepad("E:\\excelfiles\\New folder\\OutputNotepad25thjuneUthkarsh.txt");
		ReportUtils.findCommonTags(tokenziedTagInputDatabase, Obj_outputitems);

	}

}
