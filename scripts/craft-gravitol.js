const some_item = extendContent(Item, "gravitol", {
  researchRequirements(){
    return ItemStack.with(Items.gravitium, 1000, Items.thorium, 2000, Items.silicon, 5000);
  }
});