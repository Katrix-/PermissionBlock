name := "chateditor"
organization := "io.github.katrix"
version := "1.0.0"

scalaVersion := "2.11.8"

crossPaths := false

resolvers += "SpongePowered" at "https://repo.spongepowered.org/maven"
resolvers += Resolver.mavenLocal

libraryDependencies += "org.spongepowered" % "spongeapi" % "4.0.0"
libraryDependencies += "io.github.katrix" % "spongebt" % "1.1.0"

autoAPIMappings := true

assemblyShadeRules in assembly := Seq(
	ShadeRule.rename("scala.**" -> "io.github.katrix.chateditor.shade.scala.@1").inAll,
	ShadeRule.rename("io.github.katrix.spongebt.**" -> "io.github.katrix.chateditor.shade.spongebt.@1").inAll
)

scalacOptions += "-Xexperimental"