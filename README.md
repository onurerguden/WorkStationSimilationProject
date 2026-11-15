# Workstation Scheduling Simulator

A Java console simulation of workstations processing jobs through a task workflow. The simulator reads job arrivals and workflow definitions, assigns tasks to stations and advances execution through timed events.

This is my fork of the [SE116 team project](https://github.com/kagankaca/SE116). The [upstream history](https://github.com/kagankaca/SE116/commits/main/?author=onurerguden) records my implementation work.

## Run the sample

From the repository root with JDK 21 or newer, compile and launch the simulator.

```sh
mkdir -p build
javac -encoding UTF-8 -d build *.java
java -cp build Main
```

When prompted, enter `sample_workflow.txt` first and `sample_jobFile.txt` second. Press Enter to advance through events. The bundled workflow prints parser diagnostics and then the simulation reports station utilization and job tardiness. Use [the sample jobs](sample_jobFile.txt) and [sample workflow](sample_workflow.txt) as input format references.

The [main scheduling loop](Main.java), [workflow parser](Parser.java) and [event definitions](EventType.java) are the main implementation entry points.
