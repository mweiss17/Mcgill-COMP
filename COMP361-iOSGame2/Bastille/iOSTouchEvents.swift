//
//  iOSTouchEvents.swift
//  Bastille
//
//  Created by Kayhan Feroze Qaiser on 17/10/2014.
//  Copyright (c) 2014 Civet Atelier. All rights reserved.
//

import Foundation
import SpriteKit

/**
This class is simply an extension to Gamescene that handles all the touch events
**/

extension GameScene {

    override func touchesEnded(touches: NSSet, withEvent event: UIEvent) {
        /* Called when a touch begins */
        
        for touch: AnyObject in touches {
            var location = touch.locationInNode(layers[0])
            var nodes = nodesAtPoint(location)
            
            switch touchState {
            case .None:
                println("NONE")
                for node in nodes {
                    if node is Villager {
                        selectedNode = node as SKNode
                        characterSelected(node as Villager)
                    }
                    else if node is Tree {
                        println(node)
                    }
                    else if node is Tile {
                        println(node)
                    }
                }

            case .Panning:
                println("Panning")
            case .CharacterSelected:
                println("Selected")
                for node in nodes {
                    if node is Villager {
                        selectedNode = node as SKNode
                        characterDeselected(node as Villager)
                    }
                    else if node is Tree {

                    }
                    else if node is Tile {
                        moveToTile(node as Tile)
                        println(node)
                    }
                }
            }
        }
    }

    func moveToTile(tile: Tile) {
        let action = SKAction.moveTo(tile.position, duration: 0.5 )
        selectedNode.runAction(action)
        characterDeselected(selectedNode as Villager)
    }

    func characterSelected(character:Villager) {
        touchState = .CharacterSelected
        character.xScale *= 2
        character.yScale *= 2
    }
    func characterDeselected(character:Villager) {
        touchState = .None
        selectedNode = SKNode()
        character.xScale /= 2
        character.yScale /= 2
    }
    
    func panForTranslation(translation:CGPoint) {
        let position = world.position
        world.position = CGPointMake(position.x+translation.x, position.y+translation.y)
    }
    
    override func touchesMoved(touches: NSSet!, withEvent event: UIEvent!) {
        let touch = touches.anyObject() as UITouch
        let touchPoint = touch.locationInNode(world)
        let previousTouchPoint = touch.previousLocationInNode(world)
        var translation = CGPointMake(touchPoint.x - previousTouchPoint.x, touchPoint.y - previousTouchPoint.y)
        panForTranslation(translation)
        
        touchState = .None
        selectedNode = SKNode()
    }


}