//
//  GameScene.swift
//  Bastille
//
//  Created by Kayhan Feroze Qaiser on 01/10/2014.
//  Copyright (c) 2014 Civet Atelier. All rights reserved.
//

import SpriteKit

enum WorldLayer: Int {
    case Map = 0, Character, Top
}
enum TouchState {
    case None, Panning, CharacterSelected
}

let kWorldLayerCount = 5
let kMapSizeX = 30
let kMapSizeY = 30

class GameScene: SKScene {
    var world = SKNode()
    var layers = [SKNode]()
    var selectedNode = SKNode()
    var touchState = TouchState.None
    var map = Map(test: 4)
    
    override func didMoveToView(view: SKView) {
        /* Setup your scene here */
        
        world.name = "world"
        for i in 0..<kWorldLayerCount {
            let layer = SKNode()
            layer.zPosition = CGFloat(i - kWorldLayerCount)
            world.addChild(layer)
            layers.append(layer)
        }
        
        loadAssets()
        
        addChild(world)
        map.makeRandomMap()
        addNode(map, atWorldLayer: WorldLayer.Map)

        

    }

    func addNode(node: SKNode, atWorldLayer layer: WorldLayer) {
        let layerNode = layers[layer.rawValue]
        layerNode.addChild(node)
    }
    
    func loadAssets() {
        var treeSprite = SKSpriteNode(imageNamed: "pine-half08")
        var treeSprites = [treeSprite]
        sSharedTree = Tree(sprites: treeSprites)

        var tileSprite = SKSpriteNode(imageNamed: "GrassQuick")
        var tileSprites = [tileSprite]
        sSharedTile = Tile(sprites: tileSprites)
        
        var villagerSprite = SKSpriteNode(imageNamed: "villager")
        var villagerSprites = [villagerSprite]
        sSharedVillager = Villager(sprites: villagerSprites)
        
    }
    

    
    
    
    override func update(currentTime: CFTimeInterval) {
        /* Called before each frame is rendered */
    }
}


var objectSelected:Bool = false

let kWorldZoom = 1

// declare shared resources here
var sSharedTree = Tree(sprites: [SKSpriteNode]())
var sSharedTile = Tile(sprites: [SKSpriteNode]())
var sSharedVillager = Villager(sprites: [SKSpriteNode]())






