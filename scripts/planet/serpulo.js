const bmaze = new SectorPreset("magamaze", Planets.serpulo, 153);
bmaze.captureWave = 73;
bmaze.difficulty = 4;
bmaze.objectives = Seq.with(
new Objectives.SectorComplete(SectorPresets.stainedMountains),
new Objectives.Research(Vars.content.getByName(ContentType.block, "gravillaso-molecular-core")),
new Objectives.Research(Vars.content.getByName(ContentType.block, "gravillaso-gravitol-wall")),
new Objectives.Research(Blocks.laserDrill)
);