const ui = global.ui;
ui.addMenuButton("GitHub", "link", () => {
	Core.app.openURI("https://github.com/NickName73/Gravillaso")
});
ui.addMenuButton("VkGroup", "link", () => {
	Core.app.openURI("https://vk.com/gravillaso")
});
ui.addMenuButton("Trello", "link", () => {
	Core.app.openURI("https://trello.com/b/wT73AZQq/gravillaso")
});
ui.onLoad(() => {

});