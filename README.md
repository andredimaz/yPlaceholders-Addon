
# yPlaceholders

Este plugin consiste em fazer um ranking dos jogadores. Desenvolvido para a versão 1.8 da spigot, não testado em outras versões.



## Dependências

- [Vault](https://www.spigotmc.org/resources/vault.34315/)
- [yPlugins](https://ystoreplugins.com.br/client/plugins)
- [yPoints](https://ystoreplugins.com.br/plugins/gratuitos)
- [LuckPerms](https://luckperms.net/download)
- [PermissionsEx](https://www.spigotmc.org/resources/permissionsex.108323/)


## Placeholders


| Placeholders   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `{pos}` | `interna` | Retorna a posição do jogador. |
| `{tag}` | `interna` | Retorna o prefixo do jogador com base no LuckPerms ou PermissionsEx. |
| `{jogador}` | `interna` | Retorna o nome do jogador. |
| `{valor}` | `interna` | Retorna o valor que o jogador possui. |
| `%coins_top<pos>%` | `externa` | Retorna o estilo definido na secção vault. |
| `%cash_top<pos>%` | `externa` | Retorna o estilo definido na secção yPoints. |



## Funcionalidades

- Ranking atualizado em tempo real.
- Uso de placeholders externas.


## FAQ

### Será adicionado suporte para mais plugins?

Sim, caso queira adicionar suporte para algum outro plugin me contacte.

### Como posso contactar?

Através do meu discord: **@notdimaz**


## config.yml

```yml
top-nulo: "&7{pos} &8▪ &fNinguém"

vault:
    estilo: '&7{pos} &8▪ {tag}{jogador} &8▶ &2$&f{valor}'
    tamanho: 10 # Quantia de placeholders criadas para o top, %coins_top_10%

ypoints:
    estilo: '&7{pos} &8▪ {tag}{jogador} &8▶ &6✪{valor}'
    tamanho: 10 # Quantia de placeholders criadas para o top, %cash_top_10%


formatador:
    - ''
    - 'K'
    - 'M'
    - 'B'
    - 'T'
    - 'Q'
    - 'QQ'
    - 'S'
    - 'SS'
    - 'OC'
    - 'N'
    - 'UN'
    - 'D'
    - 'DD'
    - 'TR'
    - 'QT'
    - 'QN'
    - 'VG'
```

