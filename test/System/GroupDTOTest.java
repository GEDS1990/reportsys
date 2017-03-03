package System;

import java.util.ArrayList;
import java.util.List;

import com.midnight.reportsys.dto.GroupDTO;
import com.midnight.reportsys.pojo.Groups;

public class GroupDTOTest {

	public static void main(String[] args) {
		List<Groups> lists = new ArrayList<>();
		Groups groups1 = new Groups();
		groups1.setName("test2");
		groups1.setLeader("测试用户1,测试用户3");
		groups1.setMember("555,刘良");
		groups1.setIds("7,9,10,5");
		lists.add(groups1);
		Groups groups2 = new Groups();
		groups2.setName("test3");
		groups2.setLeader("测试用户4,测试用户5");
		groups2.setMember("666,刘良666,222");
		groups2.setIds("8,2,4,9,3");
		lists.add(groups2);

		List<GroupDTO> gDtos = new ArrayList<>();

		for (int i = 0; i < lists.size(); ++i) {
			Groups groups = lists.get(i);

			String name = groups.getName();
			String[] leaders = groups.getLeader().split(",");
			String[] members = groups.getMember().split(",");
			String[] groupsDTO = new String[leaders.length + members.length + 1];
			groupsDTO[0] = name;
			for (int j = 0; j < leaders.length; ++j) {
				groupsDTO[j + 1] = leaders[j];
			}
			for (int j = 0; j < members.length; ++j) {
				groupsDTO[j + 1 + leaders.length] = members[j];
			}

			String userids = groups.getIds();
			String[] _userids = userids.split(",");
			for (int j = 0; j < groupsDTO.length; j++) {
				GroupDTO g = new GroupDTO();
				if (j == 0) {
					g.setRole("组名");
					g.setName(groupsDTO[j]);
				} else if (j > 0 && j < leaders.length + 1) {
					g.setRole("组长");
					g.setName(groupsDTO[j]);
					g.setId(Integer.parseInt(_userids[j-1]));
					g.setDaily("<a href=\"statistics/memberStatistics/daily"  + "?id=" + g.getId() + "\">日报表分析</a>");
					g.setWeekly("<a href=\"statistics/memberStatistics/weekly"  + "?id=" + g.getId() + "\">周报表分析</a>");
				} else {
					g.setRole("组员");
					g.setName(groupsDTO[j]);
					g.setId(Integer.parseInt(_userids[j-1]));
					g.setDaily("<a href=\"statistics/memberStatistics/daily"  + "?id=" + g.getId() + "\">日报表分析</a>");
					g.setWeekly("<a href=\"statistics/memberStatistics/weekly"  + "?id=" + g.getId() + "\">周报表分析</a>");
				}
				gDtos.add(g);
			}

		}
		
		for(GroupDTO gDto : gDtos)
			System.out.println(gDto);

	}

}
