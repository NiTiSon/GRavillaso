const ui = global.ui;
ui.addMenuButton("$ui.github", "link", () => {
	Core.app.openURI("https://github.com/NickName73/Gravillaso")
});
ui.addMenuButton("$ui.vk", "link", () => {
	Core.app.openURI("https://vk.com/gravillaso")
});
ui.addMenuButton("$ui.trello", "link", () => {
	Core.app.openURI("https://trello.com/b/wT73AZQq/gravillaso")
});
ui.addMenuButton("$ui.discord", "link", () => {
	Core.app.openURI("data:text/html,<b>Alpha</b>")
});
ui.onLoad(() => {

});