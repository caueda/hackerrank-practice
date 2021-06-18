package hackerrank.sushiorder;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.jupiter.api.Test;

class SushiOrderManagerTest {
	@Test
	 public void itShouldWorkOnRandomTests () {
		SushiOrderManager manager = new SushiOrderManager();
		
		Queue<SushiItem> expected = queueify(new SushiItem[] {
	            new SushiItem(2, "portobello"),
	            new SushiItem(3, "kimchi"),
	            new SushiItem(4, "avocado"),
	            new SushiItem(10, "rainbow"),
	            new SushiItem(1, "asparagus")
	        });
		
		//addOrder(manager, request);
//		assertNextItem(manager, null);
//		assertNextItem(manager, null);
//		assertNextItem(manager, null);
//		assertNextItem(manager, null);
		
		ArrayList<SushiOrder> request = new ArrayList<>(
	            Arrays.asList(
	                new SushiOrder(9, // seatId for this order
	                    new ArrayList<>(Arrays.asList(
								// menuItemIds for this order
								"rainbow",
								"shiitake"))
	                ),
	                new SushiOrder(4, // seatId for this order
		                    new ArrayList<>(Arrays.asList(
									// menuItemIds for this order
									"avocado"))
		            )
	            )
	        );
		addOrder(manager, request);
		request = new ArrayList<>(
	            Arrays.asList(
	                new SushiOrder(2, // seatId for this order
	                    new ArrayList<>(Arrays.asList(
								// menuItemIds for this order
								"portobello"))
	                ),
	                new SushiOrder(12, // seatId for this order
		                    new ArrayList<>(Arrays.asList(
									// menuItemIds for this order
									"california",
									"dragon",
									"mango"))
		            )
	            )
	        );
		addOrder(manager, request);
		assertNextItem(manager, expected.poll());
		request = new ArrayList<>(
	            Arrays.asList(
	                new SushiOrder(4, // seatId for this order
	                    new ArrayList<>(Arrays.asList(
								// menuItemIds for this order
								"brown rice",
								"mango",
								"brown rice",
								"rainbow"))
	                ),
	                new SushiOrder(3, // seatId for this order
		                    new ArrayList<>(Arrays.asList(
									// menuItemIds for this order
									"kimchi"))
		            ),
	                new SushiOrder(15, // seatId for this order
		                    new ArrayList<>(Arrays.asList(
									// menuItemIds for this order
									"carrot",
									"rainbow",
									"brown rice"))
		            )
	            )
	        );
		addOrder(manager, request);
		assertNextItem(manager, expected.poll());
	}
	
	@Test
    public void itShouldMaintainSequencing() {
		Queue<SushiItem> expected = queueify(new SushiItem[] {
	            new SushiItem(1, "avocado"),
	            new SushiItem(2, "tuna"),
	            new SushiItem(10, "rainbow"),
	            new SushiItem(1, "asparagus")
	        });
		
		ArrayList<SushiOrder> request = new ArrayList<>(
	            Arrays.asList(
	                new SushiOrder(1, // seatId for this order
	                    new ArrayList<>(Arrays.asList(
								// menuItemIds for this order
								"avocado"))
	                )
	            )
	        );
	        
	        SushiOrderManager manager = new SushiOrderManager();
	        addOrder(manager, request);
	        assertNextItem(manager, expected.poll());
	        
	        request = new ArrayList<>(
		            Arrays.asList(
		                new SushiOrder(1, // seatId for this order
		                    new ArrayList<>(Arrays.asList(
									// menuItemIds for this order
									"asparagus"))
		                )
		            )
		        );
	        
	        addOrder(manager, request);
	        
	        request = new ArrayList<>(
		            Arrays.asList(
		                new SushiOrder(10,
		                    new ArrayList<>(Arrays.asList(
		                        new String[] {"rainbow"}
		                    ))
		                )
		            )
		        );
	        
	        addOrder(manager, request);
	        
	        request = new ArrayList<>(
		            Arrays.asList(
		                new SushiOrder(2,
		                    new ArrayList<>(Arrays.asList(
									"tuna"))
		                )
		            )
		        );
	        
	        addOrder(manager, request);
	        
	        for (int i = 0; i < 3; i++) {
	            assertNextItem(manager, expected.poll());
	        }
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
								// menuItemIds for this order
								"tuna", "california", "avocado"))
	                ),
	                new SushiOrder(3,
	                    new ArrayList<>(Arrays.asList(
	                        new String[] {"avocado"}
	                    ))
	                ),
	                new SushiOrder(2,
	                    new ArrayList<>(Arrays.asList(
								"salmon", "tuna", "crab", "scallop"))
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
								"dragon"))
	                ),
	                new SushiOrder(1,
	                    new ArrayList<>(Arrays.asList(
								"avocado", "dynamite"))
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
								"mushroom"))
	                )
	            )
	        );
	        addOrder(manager, request);
	        
	        for (int i = 0; i < 4; i++) {
	            assertNextItem(manager, expected.poll());
	        }
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
