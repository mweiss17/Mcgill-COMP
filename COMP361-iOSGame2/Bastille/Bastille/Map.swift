//
//  Map.swift
//  Bastille
//
//  Created by Kayhan Feroze Qaiser on 20/01/2015.
//  Copyright (c) 2015 Civet Atelier. All rights reserved.
//

import Foundation
import SpriteKit

class Map: BasicSprite {
    enum MapLayer: Int {
        case Tile = 0, Tree
    }
    
    var map = SKNode()
    var layers = [SKNode]()
    var layersCount = 2
    
    init () {
        super.init(sprites: [SKSpriteNode]())
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    convenience init (test: Int) {
        self.init()
        print("convenience INIT")
        for i in 0..<layersCount {
            let layer = SKNode()
            layer.zPosition = CGFloat(i - layersCount)
            map.addChild(layer)
            layers.append(layer)
        }
        self.addChild(map)
    }


    
    func addNode(node: SKNode, atMapLayer layer: MapLayer) {
        let layerNode = layers[layer.rawValue]
        layerNode.addChild(node)
    }
    
    func makeRandomMap() {
        let scaleFactor = 0.2
        let margin: Double = 2
        let tileWidth:Double = 110 * scaleFactor + margin
        let tileHeight:Double = 128 * scaleFactor + margin
        var tileLayer = MapLayer.Tile

        for x in 0...kMapSizeX {
            for y in 0...kMapSizeY {
                var tile:Tile = sSharedTile.copy() as Tile
                tile.xScale = 0.2
                tile.yScale = 0.2
                let xOffset = y % 2 == 0 ? 0 : tileWidth/2
                let location = CGPoint(x: Double(x)*tileWidth + xOffset, y: 0.75*Double(y)*tileHeight)
                tile.position = location
                addNode(tile, atMapLayer: tileLayer)
            }
        }
        
        addTrees()
        addVillager()
    }
    
    func addTrees() {
        let scaleFactor = 0.2
        let margin: Double = 2
        let tileWidth:Double = 110 * scaleFactor + margin
        let tileHeight:Double = 128 * scaleFactor + margin
        var treeLayer = MapLayer.Tree
        
        for x in 0...kMapSizeX {
            for y in 0...kMapSizeY {
                if (arc4random_uniform(10)<2) {
                    
                    var tree:Tree = sSharedTree.copy() as Tree
                    tree.xScale = 0.2
                    tree.yScale = 0.2
                    let xOffset = y % 2 == 0 ? 0 : tileWidth/2
                    let location = CGPoint(x: Double(x)*tileWidth + xOffset, y: 0.75*Double(y)*tileHeight + tileHeight/2)
                    tree.position = location
                    addNode(tree, atMapLayer: treeLayer)
                }
            }
        }
    }
    
    func addVillager() {
        var treeLayer = MapLayer.Tree

        var villager:Villager = sSharedVillager.copy() as Villager
        villager.xScale = 0.4
        villager.yScale = 0.4
        let location = CGPoint(x:300, y:250)
        villager.position = location
        addNode(villager, atMapLayer: treeLayer)
        
    }
    
}