package readUtils;

public class RelationshipBean {

	private TagBean tagBeanStart;
	private TagBean tagBeanEnd;
	private RelationshipType relationShipType;
	private double relationshipWeight;

	public RelationshipBean(TagBean tagBeanStart, TagBean tagBeanEnd ) {
		super();
		this.setTagBeanStart(tagBeanStart);
		this.setTagBeanEnd(tagBeanEnd);
	}

	public RelationshipType getRelationShipType() {
		return relationShipType;
	}

	public void setRelationShipType(RelationshipType relationShipType) {
		this.relationShipType = relationShipType;
	}

	// public double getRelationshipWeight() {
	// return relationshipWeight;
	// }
	//
	// public void setRelationshipWeight(double relationshipWeight) {
	// this.relationshipWeight = relationshipWeight;
	// }

	public TagBean getTagBeanStart() {
		return tagBeanStart;
	}

	public void setTagBeanStart(TagBean tagBeanStart) {
		this.tagBeanStart = tagBeanStart;
	}

	public TagBean getTagBeanEnd() {
		return tagBeanEnd;
	}

	public void setTagBeanEnd(TagBean tagBeanEnd) {
		this.tagBeanEnd = tagBeanEnd;
	}

	@Override
	public String toString() {
		System.out.println("");
		return "RelationshipBean [tagBeanStart=" + tagBeanStart + ", has relationShipType=" + relationShipType+ ", with tagBeanEnd=" + tagBeanEnd + "]";
		

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tagBeanEnd == null) ? 0 : tagBeanEnd.hashCode());
		result = prime * result + ((tagBeanStart == null) ? 0 : tagBeanStart.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RelationshipBean other = (RelationshipBean) obj;
		if (tagBeanEnd == null) {
			if (other.tagBeanEnd != null)
				return false;
		} else if (!tagBeanEnd.equals(other.tagBeanEnd))
			return false;
		if (tagBeanStart == null) {
			if (other.tagBeanStart != null)
				return false;
		} else if (!tagBeanStart.equals(other.tagBeanStart))
			return false;
		return true;
	}

}
