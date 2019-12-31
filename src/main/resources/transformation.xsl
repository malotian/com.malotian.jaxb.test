<?xml version="1.0"?>
<xsl:stylesheet
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">
	<xsl:template match="node()">
		<xsl:copy>
			<xsl:copy-of select="@*" />
			<xsl:apply-templates />
		</xsl:copy>
	</xsl:template>
	<xsl:template match="name">
		<xsl:element name="id">
			<xsl:apply-templates />
		</xsl:element>
	</xsl:template>
</xsl:stylesheet>