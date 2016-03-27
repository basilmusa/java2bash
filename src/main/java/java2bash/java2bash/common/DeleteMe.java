//package java2bash.java2bash.common;
//
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//import java2bash.java2bash.common.Strtr.ReplaceOptions;
//
//import com.google.common.base.Stopwatch;
//import com.google.common.collect.ImmutableMap;
//
//public class DeleteMe {
//	public static void main(String[] args) {
//		String test = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec fringilla magna ac nisl imperdiet, a fringilla ligula bibendum. Phasellus lobortis luctus enim. Sed sed mauris lacus. Suspendisse id auctor felis. Proin fermentum sapien velit, nec iaculis arcu ultrices a. Fusce feugiat mi sit amet erat imperdiet placerat. Duis eu ante sed leo tincidunt fringilla ut vitae massa. Aenean lorem dui, pellentesque ut diam vitae, tempus aliquam urna. Morbi at nisl in quam venenatis fermentum. Pellentesque tempus vitae lorem et dapibus. Maecenas scelerisque et lacus eu euismod. Integer imperdiet ut ligula eget ornare. Sed aliquet nulla ullamcorper feugiat consequat. Aliquam erat volutpat.\n" + 
//				"Curabitur vel urna eu justo interdum convallis. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec id erat non diam commodo efficitur. Cras aliquam vehicula lobortis. Phasellus in ipsum id magna imperdiet facilisis. Fusce faucibus justo neque, sit amet volutpat erat consequat a. Sed vitae pellentesque odio. Phasellus lobortis nulla sed fringilla pharetra. In cursus enim nec diam pretium fringilla. Nullam et dignissim lectus, in semper lectus. Sed at nisi fermentum, feugiat turpis id, semper tellus. In bibendum lacinia ex, sed placerat diam accumsan vel. Proin nec arcu sapien.\n" + 
//				"Integer viverra quis tellus at porta. In sed justo pretium, finibus ipsum id, facilisis odio. Praesent non viverra justo, vel dictum lectus. Phasellus at cursus arcu. Suspendisse at cursus leo. Donec non velit metus. Vivamus non feugiat lectus, eget gravida magna. Duis tristique velit dolor, in volutpat turpis sollicitudin non. Nunc scelerisque lorem id egestas elementum. Morbi dictum sapien sit amet mi sollicitudin finibus.\n" + 
//				"Fusce auctor congue tincidunt. Quisque scelerisque risus nec metus rutrum convallis. Mauris consectetur velit sed ligula luctus lacinia. Pellentesque bibendum diam vehicula diam scelerisque, vel facilisis quam aliquam. Vestibulum blandit faucibus elit eu maximus. Mauris fringilla tellus quis ultricies semper. Proin posuere, enim eu efficitur viverra, nunc leo lacinia mauris, vel luctus sem felis sit amet magna. Suspendisse euismod convallis magna, in feugiat lorem vehicula in. Aliquam nec tellus sed erat consectetur porta. Aenean blandit arcu non mi sodales feugiat. Fusce tristique magna eu erat iaculis, id consectetur massa venenatis.\n" + 
//				"Proin gravida, sem in tristique ullamcorper, mauris tortor ullamcorper turpis, vitae porttitor ante massa pharetra lectus. In porttitor mi in felis pretium lacinia. Pellentesque iaculis ligula odio, vitae consequat tellus placerat ac. Fusce malesuada interdum metus sit amet dictum. Ut et consectetur lacus. Ut vehicula ipsum et erat auctor rhoncus. Mauris luctus ullamcorper sem, eu ullamcorper nisl semper id. Sed libero purus, sodales id augue sed, imperdiet rutrum risus. Vivamus semper luctus magna id efficitur. Maecenas rutrum mauris congue arcu mattis, id eleifend ante vulputate. Aliquam congue ipsum sed sollicitudin ultrices. Suspendisse potenti. Integer pulvinar lacus nec metus posuere, et blandit elit dictum. Cras convallis orci egestas, tristique ligula egestas, vulputate augue. Nam lacus orci, tempor efficitur posuere a, consectetur in eros. Suspendisse lobortis nulla nunc, eget maximus arcu convallis elementum";
//		Map<String, String> replacements = ImmutableMap.of(
//					"ipsum", "Basil Saleh Abbas", 
//					"justo", "Muhammad Ali Klay", 
//					"scelerisque", "I Like sanfrancisco and California so much that sometimes I feel I miss them too but not sure why", 
//					"auctor", "Are you sure this word has a meaning or not. Please let me know.", 
//					"viverra", "Living la vida loca");
//
//		
//		/////////////////////////////////////
//		System.out.println("Apache Commons Lang Implementation Strtr Performance 1 Million Iterations");
//		
//		String[] search = new String[replacements.keySet().size()];
//		String[] replacement = new String[replacements.keySet().size()];
//		int index = 0;
//		for (String key : replacements.keySet()) {
//			search[index] = key;
//			replacement[index] = replacements.get(key);
//			index++;
//		}
//		String replaceEach = "";
//		
//		Stopwatch createStarted = Stopwatch.createStarted();
//		for (int i = 0; i < 1000000; i++)
//		{
//			replaceEach = org.apache.commons.lang3.StringUtils.replaceEach(test, search, replacement);
//		}
//		long elapsed = createStarted.elapsed(TimeUnit.MICROSECONDS);
//		System.out.println("microseconds = " + elapsed);
//
//		
//		/////////////////////////////////////
//		Stopwatch stopwatch = Stopwatch.createStarted();
//		
//		System.out.println("Basil's Implementation Strtr Performance 1 Million Iterations");
//		String something = "";
//		for (int i = 0; i < 1000000; i++)
//		{
//			something = Strtr.replaceStringUsingMap(test, replacements, ReplaceOptions.DEFAULT_MAP_ORDERING);
//		}
//		System.out.println(something);
//		
//		System.out.println("microseconds = " + stopwatch.elapsed(TimeUnit.MICROSECONDS));
//
//	}
//}
