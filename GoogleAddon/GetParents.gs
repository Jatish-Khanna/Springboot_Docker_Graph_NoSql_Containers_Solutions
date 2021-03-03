function FnctnMenuUpdateGetLocation() {
   var ss = SpreadsheetApp.getActiveSpreadsheet();
   var SSID=ss.getId();

   var fileInDocs = DocsList.getFileById(SSID);
   var folderInDocs = fileInDocs.getParents()[0].getName();

   var fileInDrive = DriveApp.getFolderById(SSID);
   var folderinDrive = fileInDrive.getParents().next().getName();

   Browser.msgBox("Docs says: " + folderInDocs +
                  ", Drive says: " + folderinDrive);
}
Document Service's .getParents() method returns an array of folder objects, while Drive's returns an iterator, 
  so the details of how to retrieve a specific parent folder(s) once you've got all of them differs.

Note that a spreadsheet may be "contained" in multiple folders... in this case, 
we've assumed that the first folder in the list is the only one - that assumption may be invalid.
