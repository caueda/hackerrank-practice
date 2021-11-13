package hackerrank.sushiorder2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.Test;

/*
	I'm not proud of this code, in the real challenge it didn't work for all tests,
	unfortunatelly I was running out of time, and I start desperately to make it pass... so it wasn't well designed
 */
class SushiOrderManagerTest {

	@Test
	public void itShouldWorkOnASeriesOfInterleavedMediumOrders() {
		Queue<SushiItem> expected = queueify(new SushiItem[] {
	            new SushiItem(1, "shiitake"),
	            new SushiItem(16, "california"),
	            new SushiItem(2, "california"),
	            new SushiItem(3, "teriyaki"),
	            new SushiItem(4, "shiitake"),
	            new SushiItem(1, "dragon")
	        });
		SushiOrderManager manager = new SushiOrderManager();
		ArrayList<SushiOrder> request = new ArrayList<>(
	            Arrays.asList(
	                new SushiOrder(16, // seatId for this order
	                    new ArrayList<>(Arrays.asList(
	                        new String[] {"california", "avocado", "dynamite", "mango", "brown rice"}))
	                ),
	                new SushiOrder(1, // seatId for this order
		                    new ArrayList<>(Arrays.asList(
		                        new String[] {"shiitake", "dragon"}))
		            )
	            )
        );
		
		addOrder(manager, request);
        assertNextItem(manager, expected.poll());//1 - shiitake
        
        request = new ArrayList<>(
	            Arrays.asList(
	                new SushiOrder(3, // seatId for this order
	                    new ArrayList<>(Arrays.asList(
	                        new String[] {"teriyaki", "tuna", "zucchini", "mushroom", "zucchini", "california"}))
	                ),
	                new SushiOrder(4, // seatId for this order
		                    new ArrayList<>(Arrays.asList(
		                        new String[] {"shiitake", "dragon"}))
		            ),
	                new SushiOrder(1, // seatId for this order
		                    new ArrayList<>(Arrays.asList(
		                        new String[] {"kimchi"}))
		            ),
	                new SushiOrder(2, // seatId for this order
		                    new ArrayList<>(Arrays.asList(
		                        new String[] {"california"}))
		            )
	            )
        );
		
		addOrder(manager, request);
		assertNextItem(manager, expected.poll());//16 - california
		
		request = new ArrayList<>(
	            Arrays.asList(
	                new SushiOrder(3, // seatId for this order
	                    new ArrayList<>(Arrays.asList(
	                        new String[] {"portobello", "cucumber"}))
	                )
	            )
        );
		
		addOrder(manager, request);
		assertNextItem(manager, expected.poll());//2, "california"
		assertNextItem(manager, expected.poll());//3, "teriyaki"
		assertNextItem(manager, expected.poll());//4, "shiitake"
		assertNextItem(manager, expected.poll());//1, "dragon"
	}
	
	@Test
	public void itShouldWorkOnASeriesOfInterleavedSingleItemOrders() {
		Queue<SushiItem> expected = queueify(new SushiItem[] {
				null,
				null,
	            new SushiItem(3, "brown rice"),
	            new SushiItem(1, "asparagus"),
	            
	            new SushiItem(2, "dragon"),
	            new SushiItem(3, "kimchi"),
	            new SushiItem(4, "california"),
	        });
		ArrayList<SushiOrder> request = new ArrayList<>(
	            Arrays.asList(
	                new SushiOrder(3, // seatId for this order
	                    new ArrayList<>(Arrays.asList(
	                        new String[] {"brown rice"}))
	                )
	            )
        );
        
        SushiOrderManager manager = new SushiOrderManager();
        
        assertNextItem(manager, expected.poll());
        assertNextItem(manager, expected.poll());
        
        addOrder(manager, request);
        
        assertNextItem(manager, expected.poll());
        
        request = new ArrayList<>(
	            Arrays.asList(
	                new SushiOrder(1, // seatId for this order
	                    new ArrayList<>(Arrays.asList(
	                        new String[] {"asparagus"}))
	                ),
	                new SushiOrder(2, // seatId for this order
		                    new ArrayList<>(Arrays.asList(
		                        new String[] {"dragon"}))
		            ),
	                new SushiOrder(3, // seatId for this order
		                    new ArrayList<>(Arrays.asList(
		                        new String[] {"kimchi"}))
		            ),
	                new SushiOrder(4, // seatId for this order
		                    new ArrayList<>(Arrays.asList(
		                        new String[] {"california"}))
		            ),
	                new SushiOrder(5, // seatId for this order
		                    new ArrayList<>(Arrays.asList(
		                        new String[] {"aspargus"}))
		            ),
	                new SushiOrder(6, // seatId for this order
		                    new ArrayList<>(Arrays.asList(
		                        new String[] {"dragon"}))
		            ),
	                new SushiOrder(7, // seatId for this order
		                    new ArrayList<>(Arrays.asList(
		                        new String[] {"kimchi"}))
		            ),
	                new SushiOrder(8, // seatId for this order
		                    new ArrayList<>(Arrays.asList(
		                        new String[] {"avocado"}))
		            ),
	                new SushiOrder(9, // seatId for this order
		                    new ArrayList<>(Arrays.asList(
		                        new String[] {"cucumber"}))
		            ),
	                new SushiOrder(10, // seatId for this order
		                    new ArrayList<>(Arrays.asList(
		                        new String[] {"carrot"}))
		            ),
	                new SushiOrder(11, // seatId for this order
		                    new ArrayList<>(Arrays.asList(
		                        new String[] {"cucumber"}))
		            ),
	                new SushiOrder(12, // seatId for this order
		                    new ArrayList<>(Arrays.asList(
		                        new String[] {"kimchi"}))
		            ),
	                new SushiOrder(13, // seatId for this order
		                    new ArrayList<>(Arrays.asList(
		                        new String[] {"rainbow"}))
		            ),
	                new SushiOrder(14, // seatId for this order
		                    new ArrayList<>(Arrays.asList(
		                        new String[] {"dynamite"}))
		            ),
	                new SushiOrder(15, // seatId for this order
		                    new ArrayList<>(Arrays.asList(
		                        new String[] {"lox"}))
		            ),
	                new SushiOrder(16, // seatId for this order
		                    new ArrayList<>(Arrays.asList(
		                        new String[] {"rainbow"}))
		            )
	            )
        );
        addOrder(manager, request);
        
        request = new ArrayList<>(
	            Arrays.asList(
	                new SushiOrder(10, // seatId for this order
	                    new ArrayList<>(Arrays.asList(
	                        new String[] {"rainbow"}))
	                )
	            )
        );
        
        addOrder(manager, request);
        
        assertNextItem(manager, expected.poll());
        
        assertNextItem(manager, expected.poll());
        assertNextItem(manager, expected.poll());
        assertNextItem(manager, expected.poll());
        
	}
	
	@Test
	public void itShouldWorkOnRandomTests() {
		SushiOrderManager manager = new SushiOrderManager();
		Queue<SushiItem> expected = queueify(new SushiItem[] {
	            new SushiItem(8, "rainbow"),
	            new SushiItem(10, "mushroom"),
	            new SushiItem(10, "tuna"),
	            new SushiItem(10, "kimchi"),
	            new SushiItem(10, "tuna")
	        });
		
		ArrayList<SushiOrder> request = new ArrayList<>(
            Arrays.asList(
                new SushiOrder(8, // seatId for this order
                    new ArrayList<>(Arrays.asList(
                        new String[] {"rainbow"}))
                ),
                new SushiOrder(10,
                		new ArrayList<>(Arrays.asList(new String[] {"mushroom", "tuna", "kimchi", "tuna"}))
                )
            )
        );        
        addOrder(manager, request);
        assertNextItem(manager, expected.poll());
        assertNextItem(manager, expected.poll());
        assertNextItem(manager, expected.poll());
        assertNextItem(manager, expected.poll());
        request = new ArrayList<>(
            Arrays.asList(
                new SushiOrder(8, // seatId for this order
                    new ArrayList<>(Arrays.asList(
                        new String[] {"zucchini"}))
                ),
                new SushiOrder(15,
                		new ArrayList<>(Arrays.asList(new String[] {"mushroom", "brown rice", "tuna", "shiitake"}))
                )
            )
        );        
        addOrder(manager, request);
        assertNextItem(manager, expected.poll());
	}
	
	@Test
    public void itShouldWorkAsAnIteratorWithASingleRequest() {
        Queue<SushiItem> expected = queueify(new SushiItem[] {
            new SushiItem(1, "tuna"),
            new SushiItem(2, "salmon"),
            new SushiItem(3, "avocado"),
            
            new SushiItem(1, "california"),
            new SushiItem(2, "tuna"),
            
            new SushiItem(1, "avocado"),
            new SushiItem(2, "crab"),
            
            new SushiItem(2, "scallop")
        });
        ArrayList<SushiOrder> request = new ArrayList<>(
            Arrays.asList(
                new SushiOrder(1, // seatId for this order
                    new ArrayList<>(Arrays.asList(
                        new String[] {"tuna", "california", "avocado" }))
                ),
                new SushiOrder(3,
                    new ArrayList<>(Arrays.asList(
                        new String[] {"avocado"}))
                ),
                new SushiOrder(2,
                    new ArrayList<>(Arrays.asList(
                        new String[] {"salmon", "tuna", "crab", "scallop"}))
                )
            )
        );
        
        SushiOrderManager manager = new SushiOrderManager();
        addOrder(manager, request);
        
        for (int i = 0; i < 8; i++) {
            assertNextItem(manager, expected.poll());
        }
    }
	
    @Test
    public void itShouldWorkWhenOrdersAndItemDeliveriesAreInterleaved() {
        Queue<SushiItem> expected = queueify(new SushiItem[] {
            new SushiItem(1, "avocado"),
            new SushiItem(2, "dragon"),
            new SushiItem(1, "dynamite"),
            new SushiItem(1, "mushroom"),
            null
        });
        ArrayList<SushiOrder> request = new ArrayList<>(
            Arrays.asList(
                new SushiOrder(2,
                    new ArrayList<>(Arrays.asList(
                        new String[] {"dragon"}
                    ))
                ),
                new SushiOrder(1,
                    new ArrayList<>(Arrays.asList(
                        new String[] {"avocado", "dynamite"}
                    ))
                )
            )
        );
        SushiOrderManager manager = new SushiOrderManager();

        addOrder(manager, request);
        assertNextItem(manager, expected.poll());
        
        request = new ArrayList<>(
            Arrays.asList(
                new SushiOrder(1,
                    new ArrayList<>(Arrays.asList(
                        new String[] {"mushroom"}
                    ))
                )
            )
        );
        addOrder(manager, request);
        
        for (int i = 0; i < 4; i++) {
            assertNextItem(manager, expected.poll());
        }
    }
    
    @Test
    public void itShouldHandleRoundsAndGroups() {
        Queue<SushiItem> expected = queueify(new SushiItem[] {
            new SushiItem(13, "rainbow"),
            new SushiItem(13, "\"tuna\""),
            new SushiItem(1, "cucumber")
        });
        
        SushiOrderManager manager = new SushiOrderManager();
        
        ArrayList<SushiOrder> request = new ArrayList<>(
            Arrays.asList(
                new SushiOrder(13,
                    new ArrayList<>(Arrays.asList(
                        new String[] {"rainbow", "\"tuna\""}
                    ))
                )
            )
        );
        
        addOrder(manager, request);
        assertNextItem(manager, expected.poll());
        
        request = new ArrayList<>(
            Arrays.asList(
                new SushiOrder(1,
                    new ArrayList<>(Arrays.asList(
                        new String[] {"cucumber1"}
                    ))
                )
            )
        );
        
        addOrder(manager, request);
        assertNextItem(manager, expected.poll());
        assertNextItem(manager, expected.poll());
    }
    
    private static void assertNextItem(
        SushiOrderManager manager, SushiItem expected
    ) {
        SushiItem item = manager.nextItem();
        System.out.println("<LOG::-Next Item>" + item);
        assertEquals(expected, item);
    }
    
    private static void addOrder(
        SushiOrderManager manager, 
        ArrayList<SushiOrder> request
    ) {
        System.out.println("<LOG::-Add Order>" + request);
        manager.addOrder(request);
    }
    
    private static Queue<SushiItem> queueify(SushiItem[] steps) {
        Queue<SushiItem> result = new LinkedList<>();
        
        for (SushiItem step : steps) {
            result.offer(step);
        }
        
        return result;
    }
}
