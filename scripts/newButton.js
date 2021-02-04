const ui = global.ui;
ui.addMenuButton("GitHub", "link", () => {
	Core.app.openURI("https://github.com/NickName73/Gravillaso")
});
ui.addMenuButton("VkGroup", "link", () => {
	Core.app.openURI("https://vk.com/gravillaso")
});
ui.onLoad(() => {

});