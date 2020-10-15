clc
clear
% Version 3.1
% Date: 1 November 2019
% Player can now win horizontally and vertically
% Fixed bug where the red and black sprite doesn't match with player


%gets nicknames of players through user input
player1 = input('Player 1, please enter your nickname: ', 's');
player2 = input('Player 2, please enter your nickname: ', 's');
%assigns color to each player
fprintf('%s, you will be red.  \n', player1)
fprintf('%s, you will be black \n', player2)

% Initialize scene
my_scene = simpleGameEngine('ConnectFour.png',86,101);

% Set up variables to name the various sprites
empty_sprite = 1;
red_sprite = 2;
black_sprite = 3;


% Display empty board
board_display = empty_sprite * ones(6,7);
drawScene(my_scene,board_display)


for(i = 1: 1: 100)
    %get mouse input
    [row,col] = getMouseInput(my_scene);
    empty = checkForSprite(row,col,board_display);
    %while the box is occupied, prompt the player to choose another box
    while(empty == false)
        [row,col] = getMouseInput(my_scene);
        %checks if box is occupied
        empty = checkForSprite(row,col,board_display);
    end
    
%display updated board with player1 choice   
if(mod(i, 2) == 1)
    board_display(row,col) = red_sprite;
end

%display updated board with player2 choice
if(mod(i, 2) ~= 1)
    board_display(row,col) = black_sprite;
end

    %displays the updated board
    drawScene(my_scene,board_display)
    if(checkWinner(row,col,board_display,red_sprite) == true)
        fprintf('Player 1 is the winner')
        break;
    end
    if(checkWinner(row,col,board_display,black_sprite) == true)
        fprintf('Player 2 is the winner')
        break;
    end
end


%checks to see if the box that the player clicked is occupied by another
%color, and also if the box below is occupied. empty is assigned with true
%if it is not occupied, and false if it is occupied
function [empty] = checkForSprite(x,y,board_display)
empty = true;
%checks if any of the box in row 6 that player chose is occupied
if(x == 6)
    if(board_display(x, y) == 1)
        empty = true;
    else
        empty = false;
    end
    %checks if the box below what the player chose is occupied
elseif(x < 6)
    if(board_display(x+1, y) == 1)
        empty = false;
    else
        %checks if any of the box in row 1-5 that player chose is occupied
        if(board_display(x, y) == 1)
            empty = true;
        else
            empty = false;
        end
    end
end
end

%returns true if num is 4, false if not 4
function x = check4(num)
x = false;
if(num == 4)
    x = true;
end
end

%checks for horizontal wins
function win = checkHourizontal(x,y,board_display,player)
totRow = 6;
totCol = 7;
win = false;

for(i = 1:1:totRow)
    if(board_display(x,y) == player)
        if( y <= totCol && y >= 1)
            %checks to the right of box
            j = 0;
            while(board_display(x,y + j) == player)
                j = j + 1;
                %check if there is 4 pieces in a row in right direction
                win = check4(j);
                %break if y is going out of array
                if( y+j > totCol)
                    break;
                end
            end
            %checks to the left of box
            k = 0;
            while(board_display(x,y - k) == player)
                k = k + 1;
                %check if there is 4 pieces in a row in both direction
                win = check4(j+k-1);
                %break if y is going out of array
                if(y-k <= 0)
                    break;
                end
            end
        end
    end
end

end

%checks for vertical wins
function win = checkVertical(x,y,board_display,player)
totRow = 6;
totCol = 7;
win = false;

for(i = 1:1:totCol)
    if(board_display(x,y) == player)
        %horizontal win
        if( x <= totRow && x >= 1)
            %checks to the right of box
            j = 0;
            while(board_display(x+j,y) == player)
                j = j + 1;
                win = check4(j);
                if( x+j > totRow)
                    break;
                end
            end
            %checks to the left of box
            k = 0;
            while(board_display(x-k,y) == player)
                k = k + 1;
                win = check4(j+k-1);
                if(x-k <= 0)
                    break;
                end
            end
        end
    end
end
end

function win = checkWinner(x,y,board_display,player)
win = false;
win = checkHourizontal(x,y,board_display,player);
if(win == false)
    win = checkVertical(x,y,board_display,player);
end

end
