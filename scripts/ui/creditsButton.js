const ui = global.uigravillaso;
ui.addMenuButton("$uigravillaso.b-github", "link", () => {
	Core.app.openURI("https://github.com/NickName73/Gravillaso")
});
ui.addMenuButton("$uigravillaso.a-vk", "link", () => {
	Core.app.openURI("https://vk.com/gravillaso")
});
ui.addMenuButton("$uigravillaso.c-trello", "link", () => {
	Core.app.openURI("https://trello.com/b/wT73AZQq/gravillaso")
});
ui.addMenuButton("$uigravillaso.d-v7download", "link", () => {
        Core.app.openURL("https://github.com/NickName73/Gravillaso/releases/download/2.1.3/GravillasoDesktop.jar")
});
ui.onLoad(() => {

});
