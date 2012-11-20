# Neuron

Interactive shell to play around with nodes.

## Installation and usage

You need [Maven][1] to build Neuron from source. To build Neuron clone this
repository and invoke Maven in the repositories base directory.

    $ git clone https://github.com/Weltraumschaf/neuron.git
    $ mvn clean install

After successful build you can run the shell.

    $ ./bin/neuron.sh

In the shell you can get all available commands with the `help` command.

    Welcome to Neuro Interactive Shell!

    > help
    This is the Neuron Interactive shell version 0.1.0-SNAPSHOT.

    Available commands:
    help                         Show all available commands.
    ...

    >

## TODO

- Possibility to add costs/latency to nodes.
- Maybe use [JUNG2][2] to visualize nodes.

[1]: http://maven.apache.org/
[2]: http://stackoverflow.com/questions/3854719/what-is-a-maven-repository-url-for-jung2-java-graph-framework