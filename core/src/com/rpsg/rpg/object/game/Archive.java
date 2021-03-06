package com.rpsg.rpg.object.game;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

import com.rpsg.rpg.object.item.BaseItem;
import com.rpsg.rpg.object.map.MapSprite;
import com.rpsg.rpg.util.Position;

/**
 * GDX-RPG 游戏存档<br>
 * 所有初始化的内容，都是从js脚本里读取的，js脚本的位置在 assets/script/system/archive.js
 */
public class Archive implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**地图*/
	public String mapName = "";
	
	/**
	 * 地图里的精灵<br>
	 * 这里用了些下三滥的方法<br>
	 * 当游戏是读档的，本变量将会有数据，否则永远都是null的，当{@link #getMapSprites() 访问}这个变量一次后，该变量将重新变为null，直到下次存档时候再次写入<br>
	 * 以此来判断当前档案是正在游玩中的，还是刚读档进来的（这样就可以根据判断选择当前精灵是从地图数据里载入，还是从存档里载入了）
	 * */
	private List<MapSprite> mapSprites = null;
	
	/**背包内的所有物品*/
	public List<BaseItem> items = new ArrayList<>();

	/**当前游戏内所有英雄*/
	public Party party = new Party();
	
	/**自定义状态*/
	Map<String, Boolean> flags = new HashMap<>();

	/**当前所在地图的相对坐标（1相对坐标 = 48像素）*/
	public Position position = new Position();
	
	/**身上的金币*/
	public long gold;
	
	/**添加一个状态*/
	public void set(String id, boolean flag){
		flags.put(id, flag);
	}
	
	/**maybe null*/
	public Boolean get(String id){
		return flags.get(id);
	}
	
	public List<MapSprite> getMapSprites() {
		List<MapSprite> list = mapSprites;
		mapSprites = null;
		return list;
	}
	
	public void setMapSprites(List<MapSprite> mapSprites) {
		this.mapSprites = mapSprites;
	}

	/**存档已进行时间(ms)**/
	public long time;


	private static final SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
	static{format.setTimeZone(TimeZone.getTimeZone("UTF-0"));}
	public String time(){
//		return null;
		return format.format((Object)time);
	}
	
}
