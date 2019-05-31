package org.ploxie.pathfinder.collision.region;

import org.ploxie.pathfinder.collision.CollisionData;
import org.ploxie.wrapper.Position;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class RegionFileIO {

    public static void saveRegion(RegionData regionData) {
        Position regionPos = regionData.getPosition();
        File file = new File("C:\\Users\\Ploxie\\.rspeer\\" + regionPos + ".wbrb");
        try {
            FileOutputStream writer = new FileOutputStream(file);

            int baseX = regionData.getPosition().getX();
            int baseY = regionData.getPosition().getY();
            int baseZ = regionData.getPosition().getZ();

            writer.write(baseX);
            writer.write(baseY);
            writer.write(baseZ);


            for (int y = 0; y < RegionData.REGION_WIDTH; y++) {
                for (int x = 0; x < RegionData.REGION_WIDTH; x++) {
                    Position worldPos = new Position((baseX * 64) + x, (baseY * 64) + y, baseZ);

                    CollisionData collisionData = regionData.get(worldPos);

                    int value = collisionData.getCollisionValue();

                    writer.write(new byte[] {
                            (byte)((value >> 24) & 0xff),
                            (byte)((value >> 16) & 0xff),
                            (byte)((value >> 8) & 0xff),
                            (byte)((value >> 0) & 0xff),
                    });
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static RegionData loadRegion(Position regionPosition) {

        File file = new File("C:\\Users\\Ploxie\\.rspeer\\" + regionPosition + ".wbrb");

        RegionData regionData = null;
        try {
            FileInputStream reader = new FileInputStream(file);

            int baseX = reader.read();
            int baseY = reader.read();
            int baseZ = reader.read();

            int x = 0;
            int y = 0;

            byte[] data = new byte[reader.available()];
            reader.read(data);
            IntBuffer buffer = ByteBuffer.wrap(data).asIntBuffer();
            int[] collisionData = new int[buffer.remaining()];
            buffer.get(collisionData);
            regionData = new RegionData(regionPosition, collisionData);

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return regionData;
    }
}
