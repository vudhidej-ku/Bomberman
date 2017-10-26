package com.thms.bomberman;

import com.almasb.fxgl.annotation.SetEntityFactory;
import com.almasb.fxgl.annotation.SpawnSymbol;
import com.almasb.fxgl.annotation.Spawns;
import com.almasb.fxgl.entity.*;
import com.almasb.fxgl.entity.component.CollidableComponent;
import com.thms.bomberman.controllers.BombController;
import com.thms.bomberman.controllers.PlayerController;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

@SetEntityFactory
public class BombermanFactory implements TextEntityFactory {

    @Spawns("BG")
    public GameEntity initBg(SpawnData data) {
        return Entities.builder()
                .at(0, 0)
                .viewFromNode(new EntityView(new Rectangle(BombermanClient.GAME_WIDTH,
                        BombermanClient.GAME_WIDTH, Color.LIGHTGREEN), RenderLayer.BACKGROUND))
                .build();
    }

    @Spawns("Player")
    public GameEntity createPlayer(SpawnData data) {
        return Entities.builder()
                .type(BombermanType.PLAYER)
                .at(50, 50)
                //.from(data)
                .viewFromNodeWithBBox(new Rectangle(blockWidth(), blockHeight(), Color.DARKBLUE))
                .with(new CollidableComponent(true))
                .with(new PlayerController())
                .build();
    }

    @Spawns("Bomb")
    public GameEntity createBomb(SpawnData data) {
        return Entities.builder()
                .type(BombermanType.BOMB)
                .from(data)
                .viewFromNodeWithBBox(new Circle(blockWidth()/2, Color.BLACK))
                .with(new BombController(data.get("radius")))
                .build();
    }

    @Spawns("PowerUp")
    public GameEntity createPowerUp(SpawnData data) {
        return Entities.builder()
                .type(BombermanType.POWERUP)
                .from(data)
                .viewFromNodeWithBBox(new Circle((blockWidth()/2)-5, Color.YELLOW))
                .with(new CollidableComponent(true))
                .build();
    }

    @SpawnSymbol('w')
    public GameEntity createWall(SpawnData data) {
        return Entities.builder()
                .type(BombermanType.WALL)
                .from(data)
                .viewFromNode(new Rectangle(blockWidth(), blockHeight(), Color.LIGHTGRAY))
                .build();
    }

    @SpawnSymbol('b')
    public GameEntity createBrick(SpawnData data) {
        return Entities.builder()
                .type(BombermanType.BRICK)
                .from(data)
                .viewFromNodeWithBBox(new Rectangle(blockWidth(), blockHeight(), Color.ORANGE))
                .build();
    }

    @Override
    public char emptyChar() {
        return 0;
    }

    @Override
    public int blockWidth() {
        return BombermanClient.TILE_SIZE;
    }

    @Override
    public int blockHeight() {
        return BombermanClient.TILE_SIZE;
    }
}
