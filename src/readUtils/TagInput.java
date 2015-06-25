package readUtils;

import java.util.Set;

public class TagInput {

	private Set<TagBean> tagBeans;
	private Set<RelationshipBean> relationshipBeans;

	public TagInput(Set<TagBean> tagBeans,Set<RelationshipBean> relationshipBeans
			) {
		super();
		this.tagBeans = tagBeans;
		this.relationshipBeans = relationshipBeans;
	}


	public Set<TagBean> getTagBeans() {
		return tagBeans;
	}

	public void setTagBeans(Set<TagBean> tagBeans) {
		this.tagBeans = tagBeans;
	}

	public Set<RelationshipBean> getRelationshipBeans() {
		return relationshipBeans;
	}

	public void setRelationshipBeans(Set<RelationshipBean> relationshipBeans) {
		this.relationshipBeans = relationshipBeans;
	}

	@Override
	public String toString() {
		return "TagInput [tagBeans=" + tagBeans + ", relationshipBeans="
				+ relationshipBeans + "]";
	}

}
