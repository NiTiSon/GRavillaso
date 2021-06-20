var namePred = (name) => boolf((node) => node.content.name == name);
var contentByName = (name) => TechTree.all.find(namePred(name)).content;
var changeParent = (child, newParent) => {
	var childNode = TechTree.get(child);
	var newParentNode = TechTree.get(newParent);
	childNode.parent.children.remove(childNode);
	newParentNode.children.add(childNode);	
};
var createRootNode = (name) => {
	var rootItem = extend(Item, name, {
		setStats() { },
		alwaysUnlocked: true
	});
	new TechTree.TechNode(TechTree.get(Blocks.coreShard), rootItem, ItemStack.empty);
	return rootItem;
};
changeParent(Blocks.diode, Blocks.powerNode);
changeParent(Blocks.surgeTower, Blocks.diode);