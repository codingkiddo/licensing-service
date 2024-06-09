
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  2.434 s
[INFO] Finished at: 2024-06-09T15:58:08+05:30
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal io.fabric8:docker-maven-plugin:0.43.4:build (default) on project licensing-service: Execution default of goal io.fabric8:docker-maven-plugin:0.43.4:build failed: No <dockerHost> given, no DOCKER_HOST environment variable, no read/writable '/var/run/docker.sock' or '//./pipe/docker_engine' and no external provider like Docker machine configured -> [Help 1]
[ERROR] 
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR] 
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/PluginExecutionException


https://stackoverflow.com/questions/39487399/docker-host-environment-variable-on-windows

12

It seems the user which is running Maven goals doesn't have access to docker.sock . The error message is telling which options are there to resolve the problem.

No <dockerHost> or <machine> given, no DOCKER_HOST environment variable, and no read/writable '/var/run/docker.sock'

Last option is the easiest one because it requires a file permission and it doesn't need to create any docker machine or set a DOCKER_HOST, On Linux you can change read/write permission of docker.sock with the following:

sudo chmod 776 /var/run/docker.sock
On windows go through this article : Microsoft article