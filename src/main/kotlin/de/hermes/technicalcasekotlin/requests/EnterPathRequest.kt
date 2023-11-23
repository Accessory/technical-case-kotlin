package de.hermes.technicalcasekotlin.requests

import de.hermes.technicalcasekotlin.models.Command
import de.hermes.technicalcasekotlin.models.Position

data class EnterPathRequest (val start:Position, val commands:List<Command>)