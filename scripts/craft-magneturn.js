const some_item = extendContent(Item, "magneturn", {
  researchRequirements(){
    return ItemStack.with(Items.gravitol, 1000, Items.gravitium, 2000, Items.surge-alloy, 1500);
  }
});