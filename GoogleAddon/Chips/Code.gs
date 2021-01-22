function onInstall() {
  onOpen();
  // show Sidebar on load
  return showSidebar();
}

/* What should the add-on do when a document is opened */
function onOpen() {
  DocumentApp.getUi()
  .createAddonMenu() // Add a new option in the Google Docs Add-ons Menu
  .addItem("Search", "showSidebar")
  .addToUi();  // Run the showSidebar function when someone clicks the menu
  // create trigger if not present
  createSpreadsheetOpenTrigger();
}

/* Show a 300px sidebar with the HTML from googlemaps.html */
function showSidebar() {
  var html = HtmlService.createTemplateFromFile("index")
    .evaluate()
    .setTitle("Keyword Planner Addon"); // The title shows in the sidebar
  return DocumentApp.getUi().showSidebar(html);
}

function createSpreadsheetOpenTrigger() {
  console.log('Project triggers length: ' +ScriptApp.getProjectTriggers().length);
  console.log('Script triggers length: ' +ScriptApp.getScriptTriggers().length);
  
  if(ScriptApp.getProjectTriggers().length > 0) {
    console.warn('No new triggers created. Found another trigger.')
    return;
  }
  var documentReference = DocumentApp.getActiveDocument();

  ScriptApp.newTrigger('showSidebar')
      .forDocument(documentReference)
      .onOpen()
      .create();
  console.log('New trigger successfully created for showSidebar function.');
}

function getLotsOfThings() {
  return [1,2,3,4,5];
}
