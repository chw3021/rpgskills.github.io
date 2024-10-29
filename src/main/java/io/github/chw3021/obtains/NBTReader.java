package io.github.chw3021.obtains;

public class NBTReader {

//
//	@EventHandler	
//	public void spawning(WorldLoadEvent ev) 
//	{
//		final World w = ev.getWorld();
//
//		HashMultimap<UUID, Location> Locs = getLocsdata().Locs;
//		
//		if(!Locs.containsKey(w.getUID())) {
//			return;
//		}
//		if(w.hasMetadata("fake")||w.hasMetadata("rpgraidworld") || !w.canGenerateStructures() || disabledWorlds.contains(w.getName())) {
//			return;
//		}
//		
//        AtomicInteger j = new AtomicInteger();
//		Bukkit.getServer().getScheduler().runTaskLater(RMain.getInstance(), new Runnable() {
//            @Override
//            public void run() 
//            {
//        		System.out.println("Spawning NPCs of World: "+w.getName() +"...");
//        		Locs.get(w.getUID()).forEach(hm -> {
//        			hm.entrySet().forEach(en->{
//        				Bukkit.getServer().getScheduler().runTaskLater(RMain.getInstance(), new Runnable() {
//        	                @Override
//        	                public void run() 
//        	                {
//        						Spawn(en.getKey(), en.getValue());
//        	                }
//        				}, j.incrementAndGet()*20); 
//        			});
//        		});
//        		Bukkit.getServer().getScheduler().runTaskLater(RMain.getInstance(), new Runnable() {
//                    @Override
//                    public void run() 
//                    {
//        				System.out.println(w.getName() +"'s NPCs Spawned");
//                    }
//        		}, j.incrementAndGet()*20); 
//            }
//		}, 100); 
//		
//	}
//	
//	@EventHandler
//	public void spawning(AsyncStructureGenerateEvent ev) {
//	    final World w = ev.getWorld();
//
//	    // 월드가 특정 메타데이터를 가지고 있거나, 구조물을 생성할 수 없거나, 비활성화된 월드라면 중지
//	    if (w.hasMetadata("fake") || w.hasMetadata("rpgraidworld") || !w.canGenerateStructures() || disabledWorlds.contains(w.getName())) {
//	        return;
//	    }
//		
//
//	    // 비동기 이벤트 안에서는 월드 변경 작업을 직접 하지 않고, 필요한 정보만 수집
//	    final Structure st = ev.getStructure();
//
//		if(w.getEnvironment() == Environment.NORMAL && (st == Structure.END_CITY || st == Structure.FORTRESS || st == Structure.BASTION_REMNANT || st == Structure.NETHER_FOSSIL || st == Structure.RUINED_PORTAL_NETHER)) {
//			return;
//		}
//		if(w.getEnvironment() == Environment.NETHER && (st != Structure.FORTRESS && st != Structure.NETHER_FOSSIL && st != Structure.BASTION_REMNANT && st != Structure.RUINED_PORTAL_NETHER)) {
//			return;
//		}
//		if(w.getEnvironment() == Environment.THE_END && st != Structure.END_CITY) {
//			return;
//		}
//	    final Location stl = new Location(w, ev.getChunkX(), 0, ev.getChunkZ());
//		
//	    // 동기 작업으로 넘겨서 실행
//	    Bukkit.getScheduler().runTask(RMain.getInstance(), () -> {
//	    	HashSet<HashMap<Location, String>> hs = new HashSet<>();
//	        HashMap<Location, String> hm = new HashMap<>();
//	        hm.put(stl, st.getKey().getKey());
//
//	        hs.add(hm);
//	        saver(w, hs); // saver는 월드 데이터를 처리하는 메서드로 가정
//
//	        Spawn(stl, st.getKey().getKey()); // Spawn은 구조물 생성을 처리하는 메서드로 가정
//	    	
////		    strct2(w,stcl,st.getKey().getKey()).forEach(gs ->{
////		    	final Location stl = gs.getBoundingBox().getMax().toLocation(w);
////		    	HashSet<HashMap<Location, String>> hs = new HashSet<>();
////		        HashMap<Location, String> hm = new HashMap<>();
////		        hm.put(stl, st.getKey().getKey());
////
////		        hs.add(hm);
////		        saver(w, hs); // saver는 월드 데이터를 처리하는 메서드로 가정
////
////		        Spawn(stl, st.getKey().getKey()); // Spawn은 구조물 생성을 처리하는 메서드로 가정
////		    });
//	    });
//	}
//
//	final private Location strct(World w,Location lel, String ns, Boolean b) {
//
//		Structure st = strrtr(ns);
//		
//		if(w.hasMetadata("fake")||w.hasMetadata("rpgraidworld") || !w.canGenerateStructures()) {
//			return null;
//		}
//		if(w.getEnvironment() == Environment.NORMAL && (st == Structure.END_CITY || st == Structure.FORTRESS || st == Structure.BASTION_REMNANT || st == Structure.NETHER_FOSSIL || st == Structure.RUINED_PORTAL_NETHER)) {
//			return null;
//		}
//		if(w.getEnvironment() == Environment.NETHER && (st != Structure.FORTRESS && st != Structure.NETHER_FOSSIL && st != Structure.BASTION_REMNANT && st != Structure.RUINED_PORTAL_NETHER)) {
//			return null;
//		}
//		if(w.getEnvironment() == Environment.THE_END && st != Structure.END_CITY) {
//			return null;
//		}
//		
//		try {
//			if(w.locateNearestStructure(lel, st, 1, b) != null) {
//				return w.locateNearestStructure(lel, st, 1, b).getLocation();
//			}
//			return null;
//		}
//		catch(NullPointerException e){
//			return null;
//		}
//	}
//
//	final private Collection<GeneratedStructure> strct2(World w,Location lel, String ns) {
//
//		Structure st = strrtr(ns);
//		
//		
//		if(w.hasMetadata("fake")||w.hasMetadata("rpgraidworld") || !w.canGenerateStructures()) {
//			return null;
//		}
//		if(st == Structure.NETHER_FOSSIL || st.getKey().getKey().contains("VILLAGE") || st == Structure.ANCIENT_CITY) {
//			return null;
//		}
//		
//		if(w.getEnvironment() == Environment.NORMAL && (st == Structure.END_CITY || st == Structure.FORTRESS || st == Structure.BASTION_REMNANT || st == Structure.NETHER_FOSSIL || st == Structure.RUINED_PORTAL_NETHER)) {
//			return null;
//		}
//		if(w.getEnvironment() == Environment.NETHER && (st != Structure.FORTRESS && st != Structure.BASTION_REMNANT && st != Structure.RUINED_PORTAL_NETHER)) {
//			return null;
//		}
//		if(w.getEnvironment() == Environment.THE_END && st != Structure.END_CITY) {
//			return null;
//		}
//		
//		try {
//			return w.getStructures(lel.getBlockX(), lel.getBlockZ(),st);
//		}
//		catch(NullPointerException e){
//			return null;
//		}
//	}
//	
//	
	
	/*final private HashSet<Structure> strset(final Environment we, Location l){
		HashSet<Structure> sths = new HashSet<>();
		if(we == Environment.NETHER) {
			sths.add(Structure.BASTION_REMNANT);
			sths.add(Structure.RUINED_PORTAL);
			sths.add(Structure.RUINED_PORTAL_NETHER);
			sths.add(Structure.FORTRESS);
		}
		else if(we == Environment.THE_END) {
			sths.add(Structure.END_CITY);
		}
		else {
			sths.add(Structure.BURIED_TREASURE);
			sths.add(Structure.PILLAGER_OUTPOST);
			sths.add(Structure.MINESHAFT);
			sths.add(Structure.RUINED_PORTAL);
			sths.add(Structure.RUINED_PORTAL_DESERT);
			sths.add(Structure.RUINED_PORTAL_JUNGLE);
			sths.add(Structure.RUINED_PORTAL_MOUNTAIN);
			sths.add(Structure.RUINED_PORTAL_OCEAN);
			sths.add(Structure.RUINED_PORTAL_SWAMP);
			sths.add(Structure.STRONGHOLD);
			final String bn = l.getBlock().getBiome().name();
			if(bn.contains("OCEAN") ||bn.contains("BEACH")) {
				sths.add(Structure.OCEAN_RUIN_COLD);
				sths.add(Structure.OCEAN_RUIN_WARM);
				sths.add(Structure.MONUMENT);
				sths.add(Structure.SHIPWRECK);
			}
			else if(bn.contains("DESERT")) {
				sths.add(Structure.DESERT_PYRAMID);
			}
			else if(bn.contains("SNOW") || bn.contains("COLD")) {
				sths.add(Structure.IGLOO);
			}
			else if(bn.contains("DEEP_DARK")) {
				sths.add(Structure.ANCIENT_CITY);
			}
			else if(bn.contains("JUNGLE")) {
				sths.add(Structure.JUNGLE_PYRAMID);
			}
			else if(bn.contains("BAD")) {
				sths.add(Structure.MINESHAFT_MESA);
			}
		}
		return sths;
	}*/
//	
//	final private HashSet<Structure> strset(){
//		if(sths.isEmpty()) {
//			//sths.add(Structure.ANCIENT_CITY);
//			sths.add(Structure.BASTION_REMNANT);
//			sths.add(Structure.BURIED_TREASURE);
//			sths.add(Structure.DESERT_PYRAMID);
//			sths.add(Structure.END_CITY);
//			sths.add(Structure.FORTRESS);
//			sths.add(Structure.IGLOO);
//			sths.add(Structure.JUNGLE_PYRAMID);
//			sths.add(Structure.MANSION);
//			sths.add(Structure.MINESHAFT);
//			sths.add(Structure.MINESHAFT_MESA);
//			sths.add(Structure.MONUMENT);
//			sths.add(Structure.OCEAN_RUIN_COLD);
//			sths.add(Structure.OCEAN_RUIN_WARM);
//			sths.add(Structure.PILLAGER_OUTPOST);
//			sths.add(Structure.RUINED_PORTAL);
//			sths.add(Structure.RUINED_PORTAL_DESERT);
//			sths.add(Structure.RUINED_PORTAL_JUNGLE);
//			sths.add(Structure.RUINED_PORTAL_MOUNTAIN);
//			sths.add(Structure.RUINED_PORTAL_OCEAN);
//			sths.add(Structure.RUINED_PORTAL_SWAMP);
//			sths.add(Structure.RUINED_PORTAL_NETHER);
//			sths.add(Structure.SHIPWRECK);
//			sths.add(Structure.STRONGHOLD);
//			sths.add(Structure.SWAMP_HUT);
//			/*sths.add(Structure.VILLAGE_DESERT);
//			sths.add(Structure.VILLAGE_PLAINS);
//			sths.add(Structure.VILLAGE_SAVANNA);
//			sths.add(Structure.VILLAGE_SNOWY);
//			sths.add(Structure.VILLAGE_TAIGA);*/
//			
//		}
//		return sths;
//	}
//    
//	final private Structure strrtr(String name) {
//		return Registry.STRUCTURE.get(NamespacedKey.minecraft(name));
//	}
////	
//	@EventHandler	
//	public void spawning(PluginEnableEvent ev) 
//	{
//		HashMultimap<UUID, Location> Locs = getLocsdata().Locs;
//        AtomicInteger j = new AtomicInteger();
//
//		Bukkit.getWorlds().forEach(w -> {
//			if(w.hasMetadata("fake")||w.hasMetadata("rpgraidworld") || !w.canGenerateStructures() || disabledWorlds.contains(w.getName())) {
//				return;
//			}
//			
//			
//			Bukkit.getServer().getScheduler().runTaskLater(RMain.getInstance(), new Runnable() {
//                @Override
//                public void run() 
//                {
//        			if(!Locs.containsKey(w.getUID())) {
//        				System.out.println("Generating NPC Save File....");
//        				HashSet<Location> hs = new HashSet<>();
//        				strset().forEach(str -> {
//        					
//        					Location hm = new Location(null, lang);
//        					
//        					Bukkit.getServer().getScheduler().runTaskLater(RMain.getInstance(), new Runnable() {
//        		                @Override
//        		                public void run() 
//        		                {
//        		                	Location stl = strct(w, new Location(w,0,64,0), str.getKey().getKey(), true);
//        							if(stl == null) {
//        								stl = strct(w, new Location(w,0,64,0), str.getKey().getKey(), false);
//        								if(stl == null) {
//        									return;
//        								}
//        							}
//        							hm.put(stl, str.getKey().getKey());
//
//        							Location stl1 = strct(w, w.getSpawnLocation(), str.getKey().getKey(), true);
//        							if(stl1 == null) {
//        								stl1 = strct(w, w.getSpawnLocation(), str.getKey().getKey(), false);
//        								if(stl1 == null) {
//        									return;
//        								}
//        							}
//        							hm.put(stl1, str.getKey().getKey());
//        							hs.add(hm);
//        		                }
//        					}, j.incrementAndGet()*20); 
//        					
//        				});
//
//        				Bukkit.getServer().getScheduler().runTaskLater(RMain.getInstance(), new Runnable() {
//        	                @Override
//        	                public void run() 
//        	                {
//        	                	if(hs.isEmpty()) {
//            	    				System.out.println("There's no Structure in " + w.getName());
//                    				return;
//        	                	}
//        	    				saver(w,hs);
//        	    				System.out.println(w.getName()+" NPC File Created");
//        	    				System.out.println("Please reload one more time after reload completed");
//        	                }
//        				}, j.incrementAndGet()*20); 
//        				return;
//        			}
//                }
//			}, 100); 
//			
//		});
//	}

}
