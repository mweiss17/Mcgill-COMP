//
//  BasicSprite.swift
//  Bastille
//
//  Created by Kayhan Feroze Qaiser on 01/10/2014.
//  Copyright (c) 2014 Civet Atelier. All rights reserved.
//

import SpriteKit

class BasicSprite: SKSpriteNode {
    
    var interactionEnabled: Bool!
    var selected: Bool = false
    
    
    convenience override init () {
        self.init(texture: nil, color: SKColor.whiteColor(), size: CGSize(width: 0, height: 0))
    }
    
    override init(texture: SKTexture?, color: SKColor?, size: CGSize) {
        super.init(texture: texture, color:color, size:size)
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }
    
    init(sprites: [SKSpriteNode]) {
        super.init(texture: nil, color: SKColor.whiteColor(), size: CGSize(width: 0, height: 0))
        
        let zOffset = 1.0 / CGFloat(sprites.count)
        
        let ourZPosition = zPosition
        for (childNumber, node) in enumerate(sprites) {
            node.zPosition = ourZPosition + (zOffset + (zOffset * CGFloat(childNumber)))
            addChild(node)
        }
    }
    
    override func copyWithZone(zone: NSZone) -> AnyObject {
        let sprite = super.copyWithZone(zone) as BasicSprite
        return sprite
    }
    
    
}