const simplex = new Packages.arc.util.noise.Simplex();
const rid = new Packages.arc.util.noise.RidgedPerlin(1, 2);

const GraviloGenerator = extend(PlanetGenerator, {
    getColor(position){
        var block = this.getBlock(position);

        Tmp.c1.set(block.mapColor).a = 1 - block.albedo;
        return Tmp.c1;
    },
    
    getBlock(pos){
        var height = this.rawHeight(pos);
    
        Tmp.v31.set(pos);
        pos = Tmp.v33.set(pos).scl(GraviloGenerator.scl);
        var rad = GraviloGenerator.scl;
        var temp = Mathf.clamp(Math.abs(pos.y * 2) / rad);
        var tnoise = simplex.octaveNoise3D(7, 0.56, 1 / 3, pos.x, pos.y + 999, pos.z);
        temp = Mathf.lerp(temp, tnoise, 0.5);
        height *= 1.2;
        height = Mathf.clamp(height);
    
        var tar = simplex.octaveNoise3D(4, 0.55, 0.5, pos.x, pos.y + 999, pos.z) * 0.3 + Tmp.v31.dst(0, 0, 1) * 0.2;
        var res = GraviloGenerator.arr[
           Mathf.clamp(Mathf.floor(temp * GraviloGenerator.arr.length), 0, GraviloGenerator.arr[0].length - 1)][ Mathf.clamp(Mathf.floor(height * GraviloGenerator.arr[0].length), 0, GraviloGenerator.arr[0].length - 1)
        ];
    
        if (tar > 0.5){
            return GraviloGenerator.tars.get(res, res);
        } else {
            return res;
        };
    },
    
    rawHeight(pos){
        var pos = Tmp.v33.set(pos);
        pos.scl(GraviloGenerator.scl);
        
        return (Mathf.pow(simplex.octaveNoise3D(7, 0.5, 1 / 3, pos.x, pos.y, pos.z), 2.3) + GraviloGenerator.waterOffset) / (1 + GraviloGenerator.waterOffset);  
    },
    
    getHeight(position){
        var height = this.rawHeight(position);
        return Math.max(height, GraviloGenerator.water);
    },
    
    genTile(position, tile){
        tile.floor = this.getBlock(position);
        tile.block = tile.floor.asFloor().wall;

        if(rid.getValue(position.x, position.y, position.z, 22) > 0.32){
            tile.block = Blocks.air;
        }
    }
});

GraviloGenerator.arr = [
    [Blocks.stone, Blocks.sand, Blocks.snow, Blocks.snow, Blocks.snow, Blocks.sand, Blocks.snow, Blocks.snow, Blocks.snow, Blocks.snow, Blocks.stone, Blocks.stone, Blocks.snow],
    [Blocks.stone, Blocks.stone, Blocks.stone, Blocks.snow, Blocks.sand, Blocks.stone, Blocks.stone, Blocks.snow, Blocks.snow, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stone],
    [Blocks.snow, Blocks.stone, Blocks.snow, Blocks.stone, Blocks.stone, Blocks.snow, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.sand, Blocks.stone],
    [Blocks.stone, Blocks.stone, Blocks.snow, Blocks.stone, Blocks.stone, Blocks.sand, Blocks.snow, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stone],  
    [Blocks.sand, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.snow, Blocks.stone, Blocks.snow, Blocks.snow, Blocks.snow, Blocks.snow, Blocks.snow, Blocks.snow, Blocks.snow],  
    [Blocks.snow, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.snow, Blocks.snow, Blocks.stone, Blocks.snow, Blocks.stone, Blocks.snow, Blocks.stone, Blocks.stone, Blocks.snow],  
    [Blocks.stone, Blocks.stone, Blocks.snow, Blocks.snow, Blocks.stone, Blocks.stone, Blocks.snow, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.snow, Blocks.sand, Blocks.sand],  
    [Blocks.stone, Blocks.snow, Blocks.snow, Blocks.snow, Blocks.stone, Blocks.stone, Blocks.snow, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.sand, Blocks.snow],  
    [Blocks.stone, Blocks.stone, Blocks.snow, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.snow, Blocks.snow, Blocks.snow, Blocks.snow, Blocks.stone, Blocks.stone],
    [Blocks.stone, Blocks.sand, Blocks.snow, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.snow, Blocks.snow, Blocks.stone, Blocks.snow, Blocks.sand], 
    [Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.sand, Blocks.stone, Blocks.stone, Blocks.sand, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stone], 
    [Blocks.stone, Blocks.stone, Blocks.stone, Blocks.sand, Blocks.sand, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.sand, Blocks.snow],
    [Blocks.sand, Blocks.stone, Blocks.snow, Blocks.stone, Blocks.snow, Blocks.snow, Blocks.snow, Blocks.snow, Blocks.stone, Blocks.stone, Blocks.snow, Blocks.sand, Blocks.sand]
];

GraviloGenerator.scl = 6.281931105;
GraviloGenerator.waterOffset = 0.02;
GraviloGenerator.basegen = new BaseGenerator();
GraviloGenerator.water = 0.07;//normal is 0.06

GraviloGenerator.dec = new ObjectMap().of(
    Blocks.stone, Blocks.sand,
    Blocks.dacite, Blocks.stone,
    Blocks.stone, Blocks.sand,
    Blocks.stone, Blocks.snow
);

GraviloGenerator.tars = new ObjectMap().of(
    Blocks.stone, Blocks.snow,
    Blocks.dacite, Blocks.sand
);

const GraviloPlanet = new JavaAdapter(Planet, {}, "gravilo", Planets.sun, 2, 0.9);

GraviloPlanet.generator = GraviloGenerator;
GraviloPlanet.startSector = 47;

GraviloPlanet.hasAtmosphere = true;
GraviloPlanet.atmosphereRadIn = 0.019;
GraviloPlanet.atmosphereRadOut = 0.29;
GraviloPlanet.atmosphereColor = Color.valueOf("63353cFF");
    
GraviloPlanet.meshLoader = prov(() => new HexMesh(GraviloPlanet, 6));
    
GraviloPlanet.orbitRadius = 45.5;
GraviloPlanet.rotateTime = 130;
GraviloPlanet.orbitTime = Mathf.pow((1.0 + 10.0 + 0.66), 1.5) * 90;

GraviloPlanet.accessible = true;//In tech tree normal is false     

const driedFields = new SectorPreset("dried-fields", Planets.serpulo, 156);
driedFields.alwaysUnlocked = true; 
driedFields.captureWave = 40;
driedFields.difficulty = 9;
const logicCenter = new SectorPreset("logical-center", Planets.serpulo, 224);
logicCenter.alwaysUnlocked = true; 
logicCenter.captureWave = 50;
logicCenter.difficulty = 7; 
const causticGorge = new SectorPreset("caustic-gorge", GraviloPlanet, 47);
causticGorge.alwaysUnlocked = true; 
causticGorge.captureWave = 50;
causticGorge.difficulty = 9;