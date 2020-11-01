const some_item = extendContent(Item, "gravitium", {
  researchRequirements(){
    return ItemStack.with(Items.titanium, 1000, Items.silicon, 1000);
  }
});