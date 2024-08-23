<?xml version="1.0" encoding="utf-8"?>
<#import "lib/macro.ftl" as m>
<doc
        xmlns="http://javacoredoc.fugerit.org"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://javacoredoc.fugerit.org https://www.fugerit.org/data/java/doc/xsd/doc-2-1.xsd" >

    <#--
        This is a Venus Fugerit Doc (https://github.com/fugerit-org/fj-doc) FreeMarker Template XML (ftl[x]).
        For consideration of Venus Fugerit Doc and Apache FreeMarker integration see :
        https://venusguides.fugerit.org/src/docs/common/doc_format_freemarker.html
        The result will be a :
    -->
    <!--
        This is a Venus Fugerit Doc (https://github.com/fugerit-org/fj-doc) XML Source Document.
        For documentation on how to write a valid Venus Doc XML Meta Model refer to :
        https://venusguides.fugerit.org/src/docs/common/doc_format_summary.html
    -->

    <#assign defaultTitle="Message fun test template">

    <metadata>
        <!-- Margin for document : left;right;top;bottom -->
        <info name="margins">10;10;10;30</info>
        <!-- documenta meta information -->
        <info name="doc-title">${docTitle!defaultTitle}</info>
        <info name="doc-subject">Template to show messageFun functionalities</info>
        <info name="doc-author">fugerit79</info>
        <info name="doc-language">en</info>
    </metadata>
    <body>
    <para>${docTitle!defaultTitle}</para>
    <para>${messageFormat(params['prop1'], 'Galadriel')}</para>
    <para><@m.simpleMacro param1='print text'/></para>
    </body>

</doc>