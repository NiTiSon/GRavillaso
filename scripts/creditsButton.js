const ui = global.ui;
ui.addMenuButton("$ui.b-github", "link", () => {
	Core.app.openURI("https://github.com/NickName73/Gravillaso")
});
ui.addMenuButton("$ui.a-vk", "link", () => {
	Core.app.openURI("https://vk.com/gravillaso")
});
ui.addMenuButton("$ui.c-trello", "link", () => {
	Core.app.openURI("https://trello.com/b/wT73AZQq/gravillaso")
});
ui.addMenuButton("$ui.d-discord", "link", () => {
	Core.app.openURI("data:text/html,<b>Alpha</b>")
});
ui.onLoad(() => {

});