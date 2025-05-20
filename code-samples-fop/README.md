# Code Samples for Apache FOP

This module was created to test behaviour of various Apache FOP versions.

1. [fj-doc-mod-fop] fail to create a PDF which is both compliant to PDF/A-1b and PDF/UA-1

The first issue has been described it : 

<https://github.com/fugerit-org/fj-doc/issues/52>

An issue on Apache FOP jira has been added : 

<https://issues.apache.org/jira/browse/FOP-3144>

This issue seems to be addressed in Apche FOP 2.10 : 

<https://xmlgraphics.apache.org/fop/2.10/changes_2.10.html>

The solution is checked with the POC class : 

`org.fugerit.java.codesamples.fop.common.FopPOC`

And the two junit testing version 2.9 and 2.10 output : 

`test.org.fugerit.java.codesamples.fop.fop29.TestFopPOC29`

`org.fugerit.java.codesamples.fop.aot.TestFopPOCAOT`

The issue seems to be actually fixed.