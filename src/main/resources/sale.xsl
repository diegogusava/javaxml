<xsl:stylesheet version="1.0"
                xmlns:jx="http://www.diegogusava.com/javaxml/schemas"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="jx:sale">

        <html>
            <body>
                <h1>Sale</h1>
                <xsl:apply-templates select="jx:products"/>
            </body>
        </html>

    </xsl:template>

    <xsl:template match="jx:products">
        <h2>Products</h2>
        <hr/>
        <xsl:apply-templates select="jx:product"/>
    </xsl:template>

    <xsl:template match="jx:product">
        <xsl:value-of select="jx:name"/><br/>
        <xsl:value-of select="jx:price"/>
    </xsl:template>

</xsl:stylesheet>